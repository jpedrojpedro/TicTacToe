<%@ page
	language="java"
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    session="true"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
	boolean ehJogador = false;
	synchronized(application)
	{
		if ( session.getAttribute("statusUsuario") != null )
		{
			if ( session.getAttribute("statusUsuario").toString().toLowerCase().contains("jogador") )
			{
				ehJogador = true;
				int qtdJogadores;
				qtdJogadores = (application.getAttribute("qtdJogadores") == null ?
				 0 : ((Integer) application.getAttribute("qtdJogadores") - 1));
				application.setAttribute("qtdJogadores", qtdJogadores);
				if ( session.getAttribute("statusUsuario").toString().equals("Jogador 1") )
					application.setAttribute("Jogador 1", false);
				else
					application.setAttribute("Jogador 2", false);
				session.setAttribute("statusUsuario", null);
			}
			else
			{
				ehJogador = false;
				session.setAttribute("statusUsuario", null);
			}
		}
	}
%>
<title>Fim de Jogo</title>
</head>
<body>
	<%
		String mensagem = (ehJogador ? "Obrigado por jogar!" : "Obrigado por assistir!");
		String resultado = (request.getAttribute("statusPartida") != null) ? 
							request.getParameter("statusPartida") : "";
	%>
	<span><%= mensagem %></span>
	<br/>
	<br/>
	<span><%= resultado %></span>
</body>
</html>