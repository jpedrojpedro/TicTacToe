package br.jpedro.puc.progweb.arbitro;

import br.jpedro.puc.progweb.excecoes.PosicaoInvalidaException;

/**
 * User: João Pedro Valladão Pinheiro - 0711730
 * Date: 05/05/2013
 * Time: 00:11
 * Classe representativa do campo de jogo.
 * O TAD utilizado será uma matriz 3x3.
 */

public class Tabuleiro
{
    private char campoDeJogo[][];

    public Tabuleiro ()
    {
        this.campoDeJogo = new char[3][3];
        this.limparTabuleiro();
    }
    
    public char obterCasa ( int linha, int coluna ) throws PosicaoInvalidaException
    {
    	if ( linha < 0 || linha > 2 || coluna < 0 || coluna > 2 )
            throw new PosicaoInvalidaException("Posição Inválida de Tabuleiro");
    	
    	if ( this.campoDeJogo[linha][coluna] != '?' )
        {
        	return this.campoDeJogo[linha][coluna];
        }
    	
    	return '\0';
    }

    public boolean inserirPeca ( int linha, int coluna, char valor ) throws PosicaoInvalidaException
    {
        if ( linha < 0 || linha > 2 || coluna < 0 || coluna > 2 )
            throw new PosicaoInvalidaException("Posição Inválida de Tabuleiro");

        if ( this.campoDeJogo[linha][coluna] == '?' )
        {
        	this.campoDeJogo[linha][coluna] = valor;
        	return true;
        }
        else
        	return false;
    }

    public void limparTabuleiro ()
    {
        for ( int i = 0; i < 3; i++ )
            for ( int j = 0; j < 3; j++ )
                this.campoDeJogo[i][j] = '?';
    }

    public char[] obterLinha ( int numLinha ) throws PosicaoInvalidaException
    {
        if ( numLinha > 2 )
            throw new PosicaoInvalidaException("Posição Inválida de Tabuleiro");

        char linha[] = new char[3];
        for ( int col = 0; col < 3; col++ )
        {
            linha[col] = this.campoDeJogo[numLinha][col];
        }
        return linha;
    }

    public char[] obterColuna ( int numColuna ) throws PosicaoInvalidaException
    {
        if ( numColuna > 2 )
            throw new PosicaoInvalidaException("Posição Inválida de Tabuleiro");

        char coluna[] = new char[3];
        for ( int linha = 0; linha < 3; linha++ )
        {
            coluna[linha] = this.campoDeJogo[linha][numColuna];
        }
        return coluna;
    }

    public char[] obterDiagonal ( boolean ehDiagonalPrincipal )
    {
        char diagonal[] = new char[3];

        if ( ehDiagonalPrincipal )
        {
            diagonal[0] = this.campoDeJogo[0][0];
            diagonal[1] = this.campoDeJogo[1][1];
            diagonal[2] = this.campoDeJogo[2][2];
        }
        else
        {
            diagonal[0] = this.campoDeJogo[0][2];
            diagonal[1] = this.campoDeJogo[1][1];
            diagonal[2] = this.campoDeJogo[2][0];
        }

        return diagonal;
    }
    
    public boolean contemCasaEmBranco ()
    {
        boolean contemCasaLivre = false;
        for ( int i = 0; i < 3; i++ )
            for ( int j = 0; j < 3; j++ )
                if ( this.campoDeJogo[i][j] == '?' )
                	contemCasaLivre = true;
        return contemCasaLivre;
    }
    
    public void imprimirTabuleiro () throws PosicaoInvalidaException
    {
        System.out.println("----------------------------");
        System.out.println(String.valueOf(this.obterLinha(0)));
        System.out.println(String.valueOf(this.obterLinha(1)));
        System.out.println(String.valueOf(this.obterLinha(2)));
        System.out.println("----------------------------");
    }
}