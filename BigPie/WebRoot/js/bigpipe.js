/**
 * 
 */
function moveFrom(fromId, toId)
{
	document.getElementById(toId).innerHTML = document.getElementById(fromId).innerHTML;
	document.getElementById(fromId).innerHTML = "";
}