/**
	User: João Pedro Valladão Pinheiro - 0711730
	Date: 05/05/2013
	Time: 01:33
	Animações do Tabuleiro.
 */

function escurecerCasa ( casa )
{
	casa.style.backgroundColor = "gray";
}

function clarearCasa ( casa )
{
	casa.style.backgroundColor = "white";
}

function enviarAcao ( casa, pagina )
{
	if ( casa != null )
		pagina.getElementById("casaSelecionada").value = casa.id;
	else
		pagina.getElementById("casaSelecionada").value = -1;
	pagina.proximaRodada.submit();
}