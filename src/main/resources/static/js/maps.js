// Table de correspondance qui associe la bonne carte au nom du continent
const mapIdContinents = [['World', 'world'], ['Africa', '002'], ['Europe', '150'], ['America', '019'], ['Asia', '142'], ['Oceania', '009'], ['North America', '021'], ['South America', '005']];
// Lancemenet de l'API Google permettant de charger les maps
google.charts.load('current', {'packages': ['geochart'], 'mapsApiKey': 'AIzaSyD-9tSrke72PouQMnMX-a7eZSW0jkFMBWY'});

// On fait l'appel AJAX dès le chargement de la page
google.charts.setOnLoadCallback(getRegionsInfo);

// Fonction qui retourne la carte associée au nom du continent
function getMap(nameContinent) {
    for (let mapContinent of mapIdContinents) {
        if (nameContinent == mapContinent[0]) {
            return mapContinent[1]
        }
    }
    return 0;
}


// Requête AJAX qui récupère les données à afficher
function getRegionsInfo() {
    $.ajax({
        type: "GET",
        url: "/api/country/continent",
        // On a pas besoin de ça vu que les données sont partagées par toutes les maps (dont la map World)
        // data: $('#formulaireContinent').serialize(), --> Mais si on décide d'afficher les cartes des pays on en aura besoin
        dataType: "json",
        contentType: "application/json",
        success: drawRegionsMap,
        error: showError
    });
}

// Function permettant d'afficher les cartes
function drawRegionsMap(result) {
    var headers = [["Country", "New Cases"]];
    for (let i = 0; i < result.length; i++) {
        headers.push(result[i]);
    }
    var dataTable = google.visualization.arrayToDataTable(headers);
    var chart = new google.visualization.GeoChart(document.getElementById('region_div'));

    // Pour chaque pays affiché, on ajoute un évènement afin de lire ses infos
    google.visualization.events.addListener(chart, 'select', function () {
        var selectedItem = chart.getSelection()[0];
        if (selectedItem) {
            var country = dataTable.getValue(selectedItem.row, 0);
            getCountryStats(country);
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
        // On fait appel à la fonction permettant de récupérer les maps
        region: getMap(document.getElementById("nameContinent").value),
        enableRegionInteractivity: true,
        colorAxis: {colors: ['rgb(29, 66, 115)']},
        backgroundColor: {colors: ['transparent']}};
    chart.draw(dataTable, options);
}




// A FAIRE
// Fonction permettant d'afficher les infos des continents quand on sélectionne leur map
document.getElementById("nameContinent").addEventListener("change", getMapsInfo());
function getMapsInfo() {
    var nameContinent = document.getElementById("nameContinent").value;
    if (nameContinent != 'World') {
        $.ajax({
            type: "GET",
            url: "/api/continent/getContinent?nameContinent=" + document.getElementById("nameContinent").value,
            dataType: "json",
            contentType: "application/json",
            success: showInfoMap,
            error: showError
        });
    } else {
        $.ajax({
            type: "GET",
            url: "/api/world/infos",
            dataType: "json",
            contentType: "application/json",
            success: showInfoMap,
            error: showError
        });
    }
}

// Fonction permettant d'afficher les infos des pays
function showInfoCountry(result) {
    var table = document.getElementById("tableInfo");
    table.style.display = "initial";
    
    var country = document.getElementById("title");
    country.innerHTML = result.name_Country;
    
    showInfo(result);
}

function showInfoMap(result) {
    var table = document.getElementById("tableInfo");
    table.style.display = "initial";
    
    var country = document.getElementById("title");
    country.innerHTML = document.getElementById("nameContinent").value;
    
    showInfo(result);
}

// Fonction permettant d'afficher les infos des continents
function showInfo(result) {
    var table = document.getElementById("tableInfo");
    table.style.display = "initial";

    var population = document.getElementById("population");
    population.innerHTML = result.population.toLocaleString();
    var cases = document.getElementById("cases");
    cases.innerHTML = result.total_Cases.toLocaleString();
    var newCases = document.getElementById("newCases");
    newCases.innerHTML = result.new_Cases.toLocaleString();
    var deaths = document.getElementById("deaths");
    deaths.innerHTML = result.total_Deaths.toLocaleString();
    var newDeaths = document.getElementById("newDeaths");
    newDeaths.innerHTML = result.new_Deaths.toLocaleString();
    var vaccinations = document.getElementById("vaccinations");
    vaccinations.innerHTML = result.total_Vaccinations.toLocaleString();
    var vaccinations2nd = document.getElementById("vaccinations2nd");
    vaccinations2nd.innerHTML = result.fully_Vaccinated.toLocaleString();
    var newVaccinations = document.getElementById("newVaccinations");
    newVaccinations.innerHTML = result.new_Vaccinations.toLocaleString();

}

//fonction qui traite les erreurs de la requête
function showError(xhr, status, message) {
    alert("Erreur: " + status + " : " + message);
}
