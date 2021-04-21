// Table de correspondance qui associe la bonne carte au nom de la zone
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
    return ['World', 'world'];
}


// Requête AJAX qui récupère les données à afficher
function getRegionsInfo() {
    var nameAttribute = document.getElementById("nameAttribute").value;
    $.ajax({
        type: "GET",
        url: "/api/country/" + nameAttribute,
        dataType: "json",
        contentType: "application/json",
        success: drawRegionsMap,
        error: showError
    });
}

// Function permettant d'afficher les cartes
function drawRegionsMap(result) {
    // Initialisation des colonnes
    var nameAttribute = document.getElementById("nameAttribute");
    var attribute = nameAttribute.options[nameAttribute.selectedIndex].text;
    var headers = [["Country", attribute]];
    
    // On ajoute chaque ligne de l'API dans une table de données
    for (let i = 0; i < result.length; i++) {
        headers.push(result[i]);
    }
    
    var dataTable = google.visualization.arrayToDataTable(headers);
    var chart = new google.visualization.GeoChart(document.getElementById('region_div'));
    var options = {
        // On fait appel à la fonction permettant de récupérer l'id des maps
        region: getMap(document.getElementById("nameContinent").value),
        enableRegionInteractivity: true,
        colorAxis: {colors: ['rgb(29, 66, 115)']},
        backgroundColor: {colors: ['transparent']},
        legend: 'New Cases'};

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
    
    // On affiche la map
    chart.draw(dataTable, options);
}




// Fonction permettant d'afficher les infos des continents quand on sélectionne leur map
document.getElementById("nameContinent").addEventListener("change", getMapsInfo());
function getMapsInfo() {
    var map = document.getElementById("nameContinent").value;
    // On vérifie que la map sélectionnée n'est pas celle de World
    if (map != 'World') {
        $.ajax({
            type: "GET",
            url: "/api/continent/getContinent?nameContinent=" + map,
            dataType: "json",
            contentType: "application/json",
            success: showInfoMap,
            error: showError
        });
    } else {
        $.ajax({
            type: "GET",
            url: "/api/world/getWorld",
            dataType: "json",
            contentType: "application/json",
            success: showInfoMap,
            error: showError
        });
    }
}

// Fonction permettant d'afficher les infos d'un Country
function showInfoCountry(result) {
    // On rend le tableau visible
    var table = document.getElementById("tableInfo");
    table.style.display = "initial";
    
    // On met en titre du tableau le nom du Country
    var country = document.getElementById("title");
    country.innerHTML = result.name;
    
    // On affiche les infos
    showInfo(result);
}

function showInfoMap(result) {
    // On rend le tableau visible
    var table = document.getElementById("tableInfo");
    table.style.display = "initial";
    
    // On met en titre du tableau le nom de la Map
    var country = document.getElementById("title");
    country.innerHTML = document.getElementById("nameContinent").value;
    
    // On affiche les infos
    showInfo(result);
}

// Fonction permettant d'afficher les infos des continents
function showInfo(result) {
    // On rend le tableau visible
    var table = document.getElementById("tableInfo");
    table.style.display = "initial";

// On récupère et affiche les infos
    var population = document.getElementById("population");
    population.innerHTML = result.pop.toLocaleString();
    var cases = document.getElementById("cases");
    cases.innerHTML = result.tcases.toLocaleString();
    var newCases = document.getElementById("newCases");
    newCases.innerHTML = result.ncases.toLocaleString();
    var deaths = document.getElementById("deaths");
    deaths.innerHTML = result.tdeaths.toLocaleString();
    var newDeaths = document.getElementById("newDeaths");
    newDeaths.innerHTML = result.ndeaths.toLocaleString();
    var vaccinations = document.getElementById("vaccinations");
    vaccinations.innerHTML = result.tvaccinations.toLocaleString();
    var vaccinations2nd = document.getElementById("vaccinations2nd");
    vaccinations2nd.innerHTML = result.fvaccinated.toLocaleString();
    var newVaccinations = document.getElementById("newVaccinations");
    newVaccinations.innerHTML = result.nvaccinations.toLocaleString();

}

// Fonction qui traite les erreurs de la requête
function showError(xhr, status, message) {
    alert("Erreur: " + status + " : " + message);
}
