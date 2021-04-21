// Lancemenet de l'API Google permettant de charger les graphes
google.charts.load('current', {'packages': ['corechart']});
// Fonction permettant de récupérer la liste des infos journalières d'un Country
function getCountryTotalStats(country) {
    $.ajax({
        type: "GET",
        url: "/api/infoDaily/country/totalstats?nameCountry=" + country,
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
                title: country + " Total Cases & Deaths",
                curveType: 'function',
                legend: {position: 'bottom'},
                width: 550,
                height: 400
            };
            var chart = new google.visualization.LineChart(document.getElementById('tgraphe'));

            // On trace le graphe
            chart.draw(dataTable, options);
        },
        error: showError
    });
}

// Fonction permettant de récupérer la liste des infos journalières d'un Country
function getCountryNewStats(country) {
    $.ajax({
        type: "GET",
        url: "/api/infoDaily/country/newstats?nameCountry=" + country,
        dataType: "json",
        contentType: "application/json",
        // Fonction permettant d'afficher les infos dans le graphe
        success: function (result) {
            // On initialise le tableau de données ainsi que les colonnes
            var dataTable = new google.visualization.DataTable();
            dataTable.addColumn('date', 'Date');
            dataTable.addColumn('number', 'New Cases');
            dataTable.addColumn('number', 'New Deaths');

            // Pour chaque ligne de l'API, on transforme les objets de la 1ère ligne en date
            for (let line of result) {
                line[0] = new Date(line[0]);
            }
            dataTable.addRows(result);

            var options = {
                title: country + " New Cases & Deaths",
                curveType: 'function',
                legend: {position: 'bottom'},
                width: 550,
                height: 400
            };
            var chart = new google.visualization.LineChart(document.getElementById('ngraphe'));

            // On trace le graphe
            chart.draw(dataTable, options);
        },
        error: showError
    });
}

// Evènement permettant de récupérer et détecter la map sélectionnée
document.getElementById("nameContinent").addEventListener("change", getAreaTotalStats());
document.getElementById("nameContinent").addEventListener("change", getAreaNewStats());
// Fonction permettant de récupérer les infos journalières d'une map
function getAreaTotalStats() {
    var map = document.getElementById("nameContinent").value;

    // On détecte si la map sélectionnée est World
    if (map != 'World') {
        $.ajax({
            type: "GET",
            url: "/api/infoDaily/continent/totalstats?nameContinent=" + map,
            dataType: "json",
            contentType: "application/json",
            success: showTotalStatsMap,
            error: showError
        });
    } else {
        $.ajax({
            type: "GET",
            url: "/api/infoDaily/world/totalstats",
            dataType: "json",
            contentType: "application/json",
            success: showTotalStatsMap,
            error: showError
        });
    }
}

// Fonction permettant de récupérer les infos journalières d'une map
function getAreaNewStats() {
    var map = document.getElementById("nameContinent").value;
    var attribute = document.getElementById("nameAttribute");
    // On détecte si la map sélectionnée est World
    if (map != 'World') {
        $.ajax({
            type: "GET",
            url: "/api/infoDaily/continent/newstats?nameContinent=" + map,
            dataType: "json",
            contentType: "application/json",
            success: showNewStatsMap,
            error: showError
        });
    } else {
        $.ajax({
            type: "GET",
            url: "/api/infoDaily/world/newstats",
            dataType: "json",
            contentType: "application/json",
            success: showNewStatsMap,
            error: showError
        });
    }
}

// Fonction permettant de tracer les graphes d'une map
function showTotalStatsMap(result) {
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
        title: document.getElementById("nameContinent").value + " Total Cases & Deaths",
        curveType: 'function',
        legend: {position: 'bottom'},
        width: 550,
        height: 400
    };
    var chart = new google.visualization.LineChart(document.getElementById('tgraphe'));

    // On trace le graphe
    chart.draw(dataTable, options);
}

// Fonction permettant de tracer les graphes d'une map
function showNewStatsMap(result) {
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
        title: document.getElementById("nameContinent").value + " New Cases & Deaths",
        curveType: 'function',
        legend: {position: 'bottom'},
        width: 550,
        height: 400
    };
    var chart = new google.visualization.LineChart(document.getElementById('ngraphe'));

    // On trace le graphe
    chart.draw(dataTable, options);
}

// Fonction qui traite les erreurs de la requête
function showError(xhr, status, message) {
    alert("Erreur: " + status + " : " + message);
}