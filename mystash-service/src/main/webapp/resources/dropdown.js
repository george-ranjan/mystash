function resetToBlankDropDown(dropdown) {

    dropdown.html("");
}

function populateDropDownData(data, valueKey, textKey, dropdown) {

    $.each(data, function (dataIndex) {

        dropdown.append($("<option></option>")
            .attr("value", data[dataIndex][valueKey])
            .text(data[dataIndex][textKey]));
    });

}

function populateDropDown(dropdown, valueKey, textKey, data) {

    resetToBlankDropDown(
        dropdown);

    populateDropDownData(
        data,
        valueKey,
        textKey,
        dropdown);
}
