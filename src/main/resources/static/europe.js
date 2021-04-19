/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

google.charts.load('current', {
    'packages': ['geochart'],
    // Note: you will need to get a mapsApiKey for your project.
    // See: https://developers.google.com/chart/interactive/docs/basic_load_libs#load-settings
    'mapsApiKey': 'AIzaSyD-9tSrke72PouQMnMX-a7eZSW0jkFMBWY'
});

google.charts.setOnLoadCallback(doAjax);

function test() {
    var result = [["China", 15.0], ['Germany', 10.0], ['Italy', 20.0], ['Spain', 50.0]];
    drawRegionsMap(result);
}
;

function doAjax() {
    $.ajax({
        type: "GET",
        url: "/api/country/getEuropeanCountries",
        dataType: "json",
        contentType: "application/json",
        success: drawRegionsMap,
        error: showError
    });
}
;

function drawRegionsMap(result) {
    var headers = [["Pays", "Cas Totaux"]];
    for (let i = 0; i < result.length; i++) {
        headers.push(result[i]);
    }
    var dataTable = google.visualization.arrayToDataTable(headers);
    var chart = new google.visualization.GeoChart(document.getElementById('region_div'));
    var options = {
        region: '150',
        colorAxis: {colors: ['rgb(29, 66, 115)']},
        backgroundColor: {colors: ['transparent']}};
    chart.draw(dataTable, options);
}
;

function showError(xhr, status, message) {
    alert("Erreur: " + status + " : " + message);
}
;
