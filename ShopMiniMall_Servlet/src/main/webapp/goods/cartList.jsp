<%@page import="org.apache.ibatis.reflection.SystemMetaObject"%>
<%@page import="com.dto.CartDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	// 삭제버튼
	$(".delBtn").click(function() {
		var num = $(this).attr("data-xxx");
		console.log(num);
		location.href="CartDelServlet?num="+num;	// CartDelServlet 으로 주문번호 넘기기
	});
	// 수정버튼(비동기처리)
	// CartUpdateServlet => 주문번호, 수량을 비동기 전송
	$(".updateBtn").click(function() {
		var num = $(this).attr("data-xxx");// 주문번호
		var gAmount = $("#cartAmount"+num).val();// 수량
		var gPrice = $(this).attr("data-price");// 가격
		console.log(num,gAmount);
		console.log(resultMesg);
		
		$.ajax({
			url: 'CartUpdateServlet',
			type: 'get',
			dataType: 'text',
			data: {
				num: num,
				gAmount: gAmount
			},
			success: function(data, status, xhr) {
				var sum = gAmount*gPrice;
				$("#sum"+num).text(sum);// 합계정보 갱신
			},
			error: function(xhr, status, error) {
				console.log(error);
			}			
		}); // end ajax
	});	// end button
	
	// 체크박스 전체선택,해제
/* 	$("#allCheck").click(function() {
		var chk = $(this).prop("checked");	// prop : 속성을 꺼내오거나 설정함
		console.log(chk);
		if($(this).prop("checked")){
			$(".check").prop("checked",true);
		}else{
			$(".check").prop("checked",false);
		}
	}); */
	$("#allCheck").click(function() {
		var result = this.checked;
		// console.log(result);	// 전체선택 true/false
		$(".check").each(function(idx, data) {
			// this.checked=result;
			data.checked=result;
		});
	}); 
	
	// Cart 전체삭제1
	$("#delAllCart").click(function() {
		var num=[];
		$(".check:checked").each(function(idx,ele) {	// 체크박스 선택된것들만 each돌림
			num[idx]=$(this).val();	// 각각 박스들의 주문번호를 num값안에 저장
		});
		console.log(num);	// (2) ['15', '16'] , 총2개선택될때
		location.href="CartDelAllServlet?data="+num;	
		// CartDelAllServlet?data=15,16,17,18 이런식으로 주문번호가 넘어감.
	});
	
	// Cart 전체삭제2
	// form이용 , 뿌려주는 데이터가 모드 form의 안에있기에 가능
	$("#delAllCart2").click(function() {
		$("form").attr("action","CartDelAllServlet2");
		$("form").submit();	// trigger , form의 바깥에있기때문에 submit이벤트 발생시켜야된다.
	});
	
	// 주문버튼 - 주문번호 넘기기
	$(".orderBtn").click(function() {
		var num =$(this).attr("data-xxx")
		console.log(num);
		location.href = "CartOrderConfirmServlet?num="+num;
	});
});// end ready
</script>
<table width="90%" cellspacing="0" cellpadding="0" border="0">

	<tr>
		<td height="30">
	</tr>

	<tr>
		<td colspan="5" class="td_default">&nbsp;&nbsp;&nbsp; <font
			size="5"><b>- 장바구니-</b></font> &nbsp;
		</td>
	</tr>

	<tr>
		<td height="15">
	</tr>

	<tr>
		<td colspan="10">
			<hr size="1" color="CCCCCC">
		</td>
	</tr>

	<tr>
		<td height="7">
	</tr>

	<tr>
		<td class="td_default" align="center">
		<input type="checkbox" name="allCheck" id="allCheck"> <strong>전체선택</strong></td>
		<td class="td_default" align="center"><strong>주문번호</strong></td>
		<td class="td_default" align="center" colspan="2"><strong>상품정보</strong></td>
		<td class="td_default" align="center"><strong>판매가</strong></td>
		<td class="td_default" align="center" colspan="2"><strong>수량</strong></td>
		<td class="td_default" align="center"><strong>합계</strong></td>
		<td></td>
	</tr>

	<tr>
		<td height="7">
	</tr>
	
	
	
	<tr>
		<td colspan="10">
			<hr size="1" color="CCCCCC">
		</td>
	</tr>


	<form name="myForm">	    
<%
   List<CartDTO> list = (List<CartDTO>)request.getAttribute("cartList");
	//System.out.println(list);
   for(int i=0 ; i<list.size();i++){
	CartDTO dto = list.get(i);
	int num = dto.getNum();
	String userid = dto.getUserid();
	String gCode = dto.getgCode();
	String gName = dto.getgName();
	int gPrice = dto.getgPrice();
	String gSize = dto.getgSize();
	String gColor = dto.getgColor();
	int gAmount = dto.getgAmount();
	String gImage = dto.getgImage();
   
%>	         
	  		<!--  <input type="text" name="num81" value="81" id="num81"> -->
	<!-- hidden부분 설정부분 	
	<input type="text" name="gImage81" value="bottom1" id="gImage81">
		 <input type="text" name="gName81" value="제나 레이스 스커트" id="gName81">
		  <input type="text" name="gSize81" value="L" id="gSize81">
		   <input type="text" name="gColor81" value="navy" id="gColor81"> 
		   <input type="text" name="gPrice81" value="9800" id="gPrice81"> -->

		<tr>
			<td class="td_default" width="80">
			<!-- checkbox는 체크된 값만 서블릿으로 넘어간다. 따라서 value에 삭제할 num값을 설정한다. -->
			<input type="checkbox"
				name="check" id="check81" class="check" value="<%=num%>"></td>
			<td class="td_default" width="80"><%=num%></td>
			<td class="td_default" width="80"><img
				src="images/items/<%=gImage%>.gif" border="0" align="center"
				width="80" /></td>
			<td class="td_default" width="300" style='padding-left: 30px'>
				<%=gName%>
				<br> <font size="2" color="#665b5f">[옵션 : 사이즈(<%=gSize%>)
					, 색상(<%=gColor%>)]
			</font></td>
			<td class="td_default" align="center" width="110">
				<%=gPrice%>
			</td>
			<td class="td_default" align="center" width="90">
				<input class="input_default" type="text" name="cartAmount"
				id="cartAmount<%=num%>" style="text-align: right" maxlength="3"
				size="2" value="<%=gAmount%>">	<!-- id뒤에 주문번호 붙여줌 -->
			</td>
			<td>
				<input type="button" value="수정" class="updateBtn" 
				data-xxx="<%=num%>" 	
				data-price="<%=gPrice%>">	<!-- 수량부분, 수정버튼 사용자 정의 속성 사용 -->
			</td>
			<td class="td_default" align="center" width="80"
				style='padding-left: 5px'><span id="sum<%=num%>">	<!-- id뒤에 주문번호 붙여줌, 합계부분 사용자 정의 속성사용 -->
				<%=gPrice*gAmount%>
				</span></td>
			<td><input type="button" value="주문" class="orderBtn" data-xxx="<%=num%>"></td>
			<td class="td_default" align="center" width="30"
				style='padding-left: 10px'>
				<input type="button" value="삭제" class="delBtn"	data-xxx="<%=num%>"> <!-- 삭제버튼이 상품수되로 생성되므로 id보다 class를 사용하고 this값 이용 -->
															<!-- data-xxx 사용자 정의 속성 -->
			</td>
			<td height="10"></td>
		</tr>

<%
	} //end for
%>

	</form>
	<tr>
		<td colspan="10">
			<hr size="1" color="CCCCCC">
		</td>
	</tr>
	<tr>
		<td height="30">
	</tr>

	<tr>
		<td align="center" colspan="5"><a class="a_black"
			href="javascript:orderAllConfirm(myForm)"> 전체 주문하기 </a>&nbsp;&nbsp;&nbsp;&nbsp; 
			<a class="a_black" href="#"  id="delAllCart"> 전체 삭제하기1 </a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a class="a_black" href="#"  id="delAllCart2"> 전체 삭제하기2 </a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a class="a_black" href="index.jsp"> 계속 쇼핑하기 </a>&nbsp;&nbsp;&nbsp;&nbsp;
		</td>
	</tr>
	<tr>
		<td height="20">
	</tr>

</table>