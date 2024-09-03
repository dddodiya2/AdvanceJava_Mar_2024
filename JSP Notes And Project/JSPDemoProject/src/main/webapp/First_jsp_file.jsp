<html>


<h1>Hello into the world of JSPs</h1>


The current time is : <%= new java.util.Date() %>


<%
 	for(int i=0; i<10; i++) {
 		out.println("</br>Printing value of i" + i);
 	}
%>


</br>
</br>
</br>


<%!
int calculateAndPrintSum(int a, int b){
	int sum = a + b;
	return sum;
}
%>





</br>


</html>