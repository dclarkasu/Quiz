$(document).ready(function(){
	console.log("Loaded");
	index();
});

//Index/Show all Quizzes function
var index = function(){
	$.ajax({
		type : 'GET',
		url : 'rest/quizzes',
		dataType : 'json'
	})
	.done(generateTable)
	.fail(function(xhr, status , err){
		console.error("Failed Index Request");
		console.error(err);
	})
};

var generateTable = function(data, status) {
	console.log(data);
	//Create table Head element content
	var $table = $('<table>');
	var $tHeaderName = $('<th>').text('Quiz Name');
	var $tHeaderView = $('<th>').text('View');
	var $tHeadRow = $('<tr>').attr('id', 'tableHead').append($tHeaderName, $tHeaderView);
	var $tHead = $('<thead>').append($tHeadRow);
	$table.append($tHead);
	
	//Create table body content
	var $tBody = $('<tbody>').attr('id', 'tableBody');
	
	data.forEach(function(quiz, idx){
		var $tDataName = $('<td>').text(quiz.name);
		var $tDataView = $('<button>').text('View').click(showQuiz);
		var $tRow = $('<tr>').append($tDataName, $tDataView);
		$tBody.append($tRow);
	});
	$table.append($tBody);
	
	$('#content').append($table);
};

var showQuiz = function(){
	console.log("View clicked");
};

