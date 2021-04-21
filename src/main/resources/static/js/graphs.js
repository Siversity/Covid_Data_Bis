// Lancemenet de l'API Google permettant de charger les graphes
google.charts.load('current', {'packages': ['corechart']});

// Fonction permettant de récupérer la liste des infos journalières d'un Country
function getCountryStats(country) {
    $.ajax({
        type: "GET",
        url: "/api/infoDaily/country/stats?nameCountry=" + country,
        dataType: "json",
        contentType: "application/json",
        // Fonction permettant d'afficher les infos dans le graphe
        success: function (result) {
            // On initialise le tableau de données ainsi que les colonnes
            var dataTable = new google.visualization.DataTable();
            dataTable.addColumn('date', 'Date');
            dataTable.addColumn('number', 'Total Cases');
            dataTable.addColumn('number', 'Total Deaths');
            
            // Pour chaque ligne de l'API, on transforme les objets de la 1ère ligne en date
            for (let line of result) {
                line[0] = new Date(line[0]);
            }
            dataTable.addRows(result);

            var options = {
                title: country,
                curveType: 'function',
                legend: {position: 'bottom'}
            };
            var chart = new google.visualization.LineChart(document.getElementById('graphe'));
            
            // On trace le graphe
            chart.draw(dataTable, options);
        },
        error: showError
    });
}

// Evènement permettant de récupérer et détecter la map sélectionnée
document.getElementById("nameContinent").addEventListener("change", getAreaStats());
// Fonction permettant de récupérer les infos journalières d'une map
function getAreaStats() {
    var map = document.getElementById("nameContinent").value;
    
    // On détecte si la map sélectionnée est World
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

// Fonction permettant de tracer les graphes d'une map
function showStatsMap(result) {
    // On initialise le tableau de données ainsi que les colonnes
    var dataTable = new google.visualization.DataTable();
    dataTable.addColumn('date', 'Date');
    dataTable.addColumn('number', 'Total Cases');
    dataTable.addColumn('number', 'Daily Cases');
    
    // Pour chaque ligne de l'API, on transforme les objets de la 1ère ligne en date
    for (let line of result) {
        line[0] = new Date(line[0]);
    }
    dataTable.addRows(result);
    
    var options = {
        title: document.getElementById("nameContinent").value,
        curveType: 'function',
        legend: {position: 'bottom'}
    };
    var chart = new google.visualization.LineChart(document.getElementById('graphe'));
    
    // On trace le graphe
    chart.draw(dataTable, options);
}

// Fonction qui traite les erreurs de la requête
function showError(xhr, status, message) {
    alert("Erreur: " + status + " : " + message);
}