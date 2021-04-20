google.charts.load('current', {'packages': ['corechart']});
function getCountryStats(country) {
    $.ajax({
        type: "GET",
        url: "/api/infoDaily/country/stats?nameCountry=" + country,
        // On a pas besoin de ça vu que les données sont partagées par toutes les maps (dont la map World)
        // data: $('#formulaireContinent').serialize(), --> Mais si on décide d'afficher les cartes des pays on en aura besoin
        dataType: "json",
        contentType: "application/json",
        success: function (result) {
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

document.getElementById("nameContinent").addEventListener("change", getAreaStats());
function getAreaStats() {
    var map = document.getElementById("nameContinent").value;
    if (map != 'World') {
        $.ajax({
            type: "GET",
            url: "/api/infoDaily/continent/stats?nameContinent=" + map,
            dataType: "json",
            contentType: "application/json",
            success: showStatsMap,
            error: showError
        });
    } else {
        $.ajax({
            type: "GET",
            url: "/api/infoDaily/world/stats",
            dataType: "json",
            contentType: "application/json",
            success: showStatsMap,
            error: showError
        });
    }
}

function showStatsMap(result) {
    var dataTable = new google.visualization.DataTable();
    dataTable.addColumn('date', 'Date');
    dataTable.addColumn('number', 'Total Cases');
    dataTable.addColumn('number', 'Daily Cases');
    for (let line of result) {
        line[0] = new Date(line[0]);
    }
    dataTable.addRows(result);
    //var chart = new google.visualization.LineChart(document.getElementById('graphe'));
    var options = {
        title: document.getElementById("nameContinent").value,
        curveType: 'function',
        legend: {position: 'bottom'}
    };
    var chart = new google.visualization.LineChart(document.getElementById('graphe'));
    chart.draw(dataTable, options);
}


//fonction qui traite les erreurs de la requête
function showError(xhr, status, message) {
    alert("Erreur: " + status + " : " + message);
}