(function($) {

  var COUNTRIES_ENDPOINT = "http://localhost:8080/countries";
  var CUSTOMERS_ENDPOINT = "http://localhost:8080/customers"

  function getCountries(callback) {
    $.get(COUNTRIES_ENDPOINT, callback);
  }

  function getPhoneNumbers(countries, state, callback) {
    $.ajax({
      url: CUSTOMERS_ENDPOINT,
      type: 'GET',
      data: {
        countries: countries,
        valid: state
      },
      success: callback
    });
  }

  function updateTable(data) {
    $("#result-table > tbody").html("");

    for (var i = 0; i < data.length; i++) {
      var phone = data[i];
      var row = '<tr><th scope="row">' + (i + 1) + '</th><td>' + phone.number + '</td><td>' + phone.country + '</td><td>' + phone.dialCode + '</td><td>' + phone.valid + '</td></tr>';
      $('#result-table tbody').append(row);
    }
  }

  function onSearchButtonClick() {
    var countries = $('#countries').val();
    var state = $('#phone-state').val();
    getPhoneNumbers(countries, state, updateTable)
  }

  function initCountrySelector(data) {
    for (var index in data) {
      var country = data[index];
      $("#countries").append('<option value="' + country +'">' + country + '</option>');
    }
    onSearchButtonClick();
  }

  $(document).ready(function() {
    getCountries(initCountrySelector);
    $('#search-button').click(onSearchButtonClick);
  });

})(jQuery);