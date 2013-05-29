package br.jpedro.puc.progweb.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.jpedro.puc.progweb.arbitro.Tabuleiro;
import br.jpedro.puc.progweb.excecoes.PosicaoInvalidaException;

/**
 * User: João Pedro Valladão Pinheiro - 0711730
 * Date: 05/05/2013
 * Time: 20:34
 * Classe que herda de Servlet responsável pelo controle do fluxo de jogo.
 */

@WebServlet(name = "ControleDoJogo", 
			description = "Responsável pelo controle do fluxo de jogo", 
			urlPatterns = { "/ControleDoJogo/*" })

public class ControleDoJogo extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private static Tabuleiro campoDeJogo;
	private static boolean vezJogador1 = true;

    public ControleDoJogo()
    {
    	super();
    	campoDeJogo = new Tabuleiro();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, 
																						  IOException
	{
		super.doGet(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, 
																						   IOException
	{
		this.controlarFluxo(request, response);
	}
	
	private void controlarFluxo ( HttpServletRequest request, HttpServletResponse response ) throws 
																						IOException, 
																						ServletException
	{
		String statusUsuario = request.getParameter("statusUsuario");
		String casaSelecionada = request.getParameter("casaSelecionada");
		char peca = '?';
		String statusPartida = null;
		try
		{
			if ( !statusUsuario.toLowerCase().contains("espectador") )
			{
				peca = ( statusUsuario.toLowerCase().equals("jogador 1") ? 
							  'X' : 'O' );
				
				if ( vezJogador1 && peca == 'O' )
					return;
				else if ( !vezJogador1 && peca == 'X' )
					return;
				
				if ( !casaSelecionada.equals("-1") )
				{
					String pos[] = casaSelecionada.split(",");
					if ( campoDeJogo.inserirPeca(Integer.parseInt(pos[0]), 
												 Integer.parseInt(pos[1]), 
												 peca)
						)
						vezJogador1 = (vezJogador1 == true) ? false : true;
				}
				
				statusPartida = analisarTabuleiro();
			}
			
			if ( statusPartida != null )
			{
				RequestDispatcher dispatcher = request.getRequestDispatcher("/page/encerramento.jsp");
				request.setAttribute("statusPartida", statusPartida);
				dispatcher.forward(request, response);
			}
			else
			{
				RequestDispatcher dispatcher = request.getRequestDispatcher("/page/tabuleiro.jsp");
				request.setAttribute("tabuleiro", campoDeJogo);
				dispatcher.forward(request, response);
			}
		}
		catch (PosicaoInvalidaException e)
		{
			response.sendRedirect("/trab2/page/oops.jsp");
		}
	}
	
	private String verificarVitoria ( String value )
    {
        if ( value.equals("XXX") )
            return "Jogador 1 venceu";
        else if ( value.equals("OOO") )
            return "Jogador 2 venceu";
        else
            return null;
    }
	
	private String analisarTabuleiro () throws PosicaoInvalidaException
    {
        String linhasTabuleiro[] = {String.valueOf(campoDeJogo.obterLinha(0)),
        							String.valueOf(campoDeJogo.obterLinha(1)),
        							String.valueOf(campoDeJogo.obterLinha(2))};
        String colunasTabuleiro[] = {String.valueOf(campoDeJogo.obterColuna(0)),
        							 String.valueOf(campoDeJogo.obterColuna(1)),
        							 String.valueOf(campoDeJogo.obterColuna(2))};
        String diagonalPrincipal = String.valueOf(campoDeJogo.obterDiagonal(true));
        String diagonalSecundaria = String.valueOf(campoDeJogo.obterDiagonal(false));
        String messagem;

        for ( int i = 0; i < linhasTabuleiro.length; i++ )
        {
            if ( ( messagem = this.verificarVitoria(linhasTabuleiro[i]) ) != null )
                return messagem;
        }

        for ( int i = 0; i < colunasTabuleiro.length; i++ )
        {
            if ( ( messagem = this.verificarVitoria(colunasTabuleiro[i]) ) != null )
                return messagem;
        }

        if ( ( messagem = this.verificarVitoria(diagonalPrincipal) ) != null )
            return messagem;
        else if ( ( messagem = this.verificarVitoria(diagonalSecundaria) ) != null )
            return messagem;
        else if ( !campoDeJogo.contemCasaEmBranco() )
        	return "Deu Velha";
        else
            return null;
    }
}
