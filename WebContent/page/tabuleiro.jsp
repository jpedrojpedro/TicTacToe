<%@page import="br.jpedro.puc.progweb.arbitro.Tabuleiro"%>
<%@ page
	language="java"
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    session="true"
    errorPage="oops.jsp"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!--
	User: João Pedro Valladão Pinheiro - 0711730
	Date: 05/05/2013
	Time: 00:26
	Página em que o usuário interage com o Jogo.
	Representação gráfica do Tabuleiro. 
 -->
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Jogo da Velha</title>
<link rel="stylesheet" type="text/css" href="/trab2/css/pagina.css">
<script type="text/javascript" src="/trab2/js/tabuleiro.js"></script>
<%
	Tabuleiro tabuleiro = (Tabuleiro) request.getAttribute("tabuleiro");
	String statusPartida = (String) request.getAttribute("statusPartida");
%>
</head>
<body>
	<div id="envelope">
		<div id="cabecalho">
			<span>
				Status Usuário: <%= session.getAttribute("statusUsuario") %>
			</span>
			&nbsp;&nbsp;
			<span id="timeout"></span>
			<br/>
		</div>
		<div id="jogo">
			<div id="0,0" class="casa" style="border-right: 5px solid black; 
											  border-bottom: 5px solid black;"
				 onmouseover="escurecerCasa(this)" onmouseout="clarearCasa(this)"
				 onclick="enviarAcao(this, document)">
				 <span class="peca">
				 	<% 
				 		if ( tabuleiro != null )
				 			out.print(tabuleiro.obterCasa(0,0));
				 	%>
				 </span>
			</div>
			<div id="0,1" class="casa" style="border-right: 5px solid black; 
											  border-bottom: 5px solid black;"
				 onmouseover="escurecerCasa(this)" onmouseout="clarearCasa(this)"
				 onclick="enviarAcao(this, document)">
				 <span class="peca">
				 	<%
				 		if ( tabuleiro != null )
				 			out.print(tabuleiro.obterCasa(0,1));
				 	%>
				 </span>
			</div>
			<div id="0,2" class="casa" style="border-bottom: 5px solid black;"
				 onmouseover="escurecerCasa(this)" onmouseout="clarearCasa(this)"
				 onclick="enviarAcao(this, document)">
				 <span class="peca">
				 	<% 
				 		if ( tabuleiro != null )
				 			out.print(tabuleiro.obterCasa(0,2));
				 	%>
				 </span>
			</div>
			<div id="1,0" class="casa" style="border-right: 5px solid black; 
											  border-bottom: 5px solid black;"
				 onmouseover="escurecerCasa(this)" onmouseout="clarearCasa(this)"
				 onclick="enviarAcao(this, document)">
				 <span class="peca">
				 	<% 
				 		if ( tabuleiro != null )
				 			out.print(tabuleiro.obterCasa(1,0));
				 	%>
				 </span>
			</div>
			<div id="1,1" class="casa" style="border-right: 5px solid black; 
											  border-bottom: 5px solid black;"
				 onmouseover="escurecerCasa(this)" onmouseout="clarearCasa(this)"
				 onclick="enviarAcao(this, document)">
				 <span class="peca">
				 	<% 
				 		if ( tabuleiro != null )
				 			out.print(tabuleiro.obterCasa(1,1));
				 	%>
				 </span>
			</div>
			<div id="1,2" class="casa" style="border-bottom: 5px solid black;"
				 onmouseover="escurecerCasa(this)" onmouseout="clarearCasa(this)"
				 onclick="enviarAcao(this, document)">
				 <span class="peca">
				 	<%
				 		if ( tabuleiro != null )
				 			out.print(tabuleiro.obterCasa(1,2));
				 	%>
				 </span>
			</div>
			<div id="2,0" class="casa" style="border-right: 5px solid black;"
				 onmouseover="escurecerCasa(this)" onmouseout="clarearCasa(this)"
				 onclick="enviarAcao(this, document)">
				 <span class="peca">
				 	<% 
				 		if ( tabuleiro != null )
				 			out.print(tabuleiro.obterCasa(2,0));
				 	%>
				 </span>
			</div>
			<div id="2,1" class="casa" style="border-right: 5px solid black;"
				 onmouseover="escurecerCasa(this)" onmouseout="clarearCasa(this)"
				 onclick="enviarAcao(this, document)">
				 <span class="peca">
				 	<% 
				 		if ( tabuleiro != null )
				 			out.print(tabuleiro.obterCasa(2,1));
				 	%>
				 </span>
			</div>
			<div id="2,2" class="casa" onmouseover="escurecerCasa(this)"
				 onmouseout="clarearCasa(this)" 
				 onclick="enviarAcao(this, document)">
				 <span class="peca">
				 	<% 
				 		if ( tabuleiro != null )
				 			out.print(tabuleiro.obterCasa(2,2));
				 	%>
				 </span>
			</div>
		</div>
	</div>
	<form method="post" name="proximaRodada" action="/trab2/ControleDoJogo">
		<input type="hidden" name="statusUsuario" 
			   value="<%= session.getAttribute("statusUsuario") %>"/>
		<input type="hidden" id="casaSelecionada" name="casaSelecionada" value="-1"/>
	</form>
</body>
<script type="text/javascript">
	var segundos = 2;
	var aux = setInterval( function() { timeToRefresh(); } , 1000 );
	function timeToRefresh()
	{
		document.getElementById("timeout").innerHTML = "Tempo de atualização do Tabuleiro: " + segundos--;
		if ( segundos == -1 )
			enviarAcao(null, document);
	}
</script>
</html>