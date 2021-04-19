const mapIdContinents = [['Africa', 002], ['Europe', 150], ['America', 019], ['Asia', 142], ['Oceania', 009]];

google.charts.load('current', {
    'packages': ['geochart'],
    // Note: you will need to get a mapsApiKey for your project.
    // See: https://developers.google.com/chart/interactive/docs/basic_load_libs#load-settings
    'mapsApiKey': 'AIzaSyD-9tSrke72PouQMnMX-a7eZSW0jkFMBWY'
});

google.charts.setOnLoadCallback(getRegionsInfo);

function getMap(nameContinent) {
    for (let mapContinent of mapIdContinents) {
        if (nameContinent == mapContinent[0]) {
            return mapContinent[1]
        }
    }
    return 0;
}
;


function getRegionsInfo() {
    $.ajax({
        type: "GET",
        url: "/api/country/continent?nameContinent=" + nameContinent,
        data: $("#choixCarte").serialize(),
        dataType: "json",
        contentType: "application/json",
        success: drawRegionsMap,
        error: showError
    });
}
;

function drawRegionsMap(result) {
    var headers = [["Pays", "Nouveaux Cas"]];
    for (let i = 0; i < result.length; i++) {
        headers.push(result[i]);
    }
    var dataTable = google.visualization.arrayToDataTable(headers);
    var chart = new google.visualization.GeoChart(document.getElementById('region_div'));

    google.visualization.events.addListener(chart, 'select', function () {
        var selectedItem = chart.getSelection()[0];
        if (selectedItem) {
            var country = dataTable.getValue(selectedItem.row, 0);
            console.log(country);
            $.ajax({
                type: "GET",
                url: "/api/country/getCountry?nameCountry=" + country,
                dataType: "json",
                contentType: "application/json",
                success: showInfoCountry,
                error: showError
            });
        }
    });

    var options = {
        region: '150',
        enableRegionInteractivity: true,
        colorAxis: {colors: ['rgb(29, 66, 115)']},
        backgroundColor: {colors: ['transparent']}};
    chart.draw(dataTable, options);
}
;

function showInfoCountry(result) {
    
    var country = document.getElementById("country");
    country.innerHTML = result.name_Country;
    
    var population = document.getElementById("population");
    population.innerHTML = result.population;
    
    var cases = document.getElementById("cases");
    cases.innerHTML = result.total_Cases;
    
    var newCases = document.getElementById("newCases");
    newCases.innerHTML = result.new_Cases;
    
    var deaths = document.getElementById("deaths");
    deaths.innerHTML = result.total_Deaths;
    
    var newDeaths = document.getElementById("newDeaths");
    newDeaths.innerHTML = result.new_Deaths;
}
;

function showError(xhr, status, message) {
    alert("Erreur: " + status + " : " + message);
}
;
