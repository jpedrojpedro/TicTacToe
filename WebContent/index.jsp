<%@ page
	language="java"
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    session="true"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!--
	User: João Pedro Valladão Pinheiro - 0711730
	Date: 05/05/2013
	Time: 00:26
	Primeira página da aplicação. Verifica se há vaga no jogo
	e redireciona para o Servlet. 
 -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bem-Vindo ao Jogo da Velha</title>
<%
	synchronized(application)
	{
		if ( application.getAttribute("qtdJogadores") == null )
		{
			application.setAttribute("qtdJogadores", 1);
			application.setAttribute("Jogador 1", true);
			session.setAttribute("statusUsuario", "Jogador 1");
		}
		else if ( (Integer) application.getAttribute("qtdJogadores") == 1 )
		{
			if ( (Boolean) application.getAttribute("Jogador 1") )
			{
				application.setAttribute("Jogador 2", true);
				session.setAttribute("statusUsuario", "Jogador 2");
			}
			else
			{
				application.setAttribute("Jogador 1", true);
				session.setAttribute("statusUsuario", "Jogador 1");
			}
			application.setAttribute("qtdJogadores", 2);
		}
		else
		{
			session.setAttribute("statusUsuario", "Espectador");
		}
	}
	response.sendRedirect("page/tabuleiro.jsp");
%>
</head>
<body></body>
</html>