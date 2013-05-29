package br.jpedro.puc.progweb.excecoes;

/**
 * User: João Pedro Valladão Pinheiro - 0711730
 * Date: 05/05/2013
 * Time: 00:09
 * Classe que herda de Exception e trata possível tentativa de
 * acesso a uma posição inválida para do Tabuleiro.
 */

public class PosicaoInvalidaException extends Exception
{
    private static final long serialVersionUID = -9094972895120036727L;

    public PosicaoInvalidaException ()
    {
        super ();
    }

    public PosicaoInvalidaException ( String message )
    {
        super ( message );
    }

    public PosicaoInvalidaException ( String message, Throwable cause )
    {
        super ( message, cause );
    }

    public PosicaoInvalidaException ( Throwable cause )
    {
        super ( cause );
    }
}