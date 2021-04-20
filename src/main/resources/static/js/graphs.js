google.charts.load('current', {'packages': ['corechart']});
function getCountryStats(country) {
    $.ajax({
        type: "GET",
        url: "/api/infoDaily/stats?nameCountry=" + country,
        // On a pas besoin de ça vu que les données sont partagées par toutes les maps (dont la map World)
        // data: $('#formulaireContinent').serialize(), --> Mais si on décide d'afficher les cartes des pays on en aura besoin
        dataType: "json",
        contentType: "application/json",
        success: function (result) {
            console.log(country)
            var dataTable = new google.visualization.DataTable();
            dataTable.addColumn('date', 'Date');
            dataTable.addColumn('number', 'Total Cases');
            dataTable.addColumn('number', 'Total Deaths');
            for (let line of result) {
                line[0] = new Date(line[0]);
            }
            dataTable.addRows(result);
            //var chart = new google.visualization.LineChart(document.getElementById('graphe'));
            var options = {
                title: country,
                curveType: 'function',
                legend: {position: 'bottom'}
            };
            var chart = new google.visualization.LineChart(document.getElementById('graphe'));
            chart.draw(dataTable, options);
        },
        error: showError
    });
}

function getAreaStats(map) {
    
}


//fonction qui traite les erreurs de la requête
function showError(xhr, status, message) {
    alert("Erreur: " + status + " : " + message);
}