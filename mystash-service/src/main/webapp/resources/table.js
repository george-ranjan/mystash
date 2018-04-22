
function resetToBlankTable(table, headers) {

    // Empty the table
    table.html("");

    // Header with Empty Body
    var blankTableHtml = "<thead><tr>";
    for (var colIndex in headers) {

        if (!headers.hasOwnProperty(colIndex)) {
            continue;
        }

        blankTableHtml = blankTableHtml + "<th>";
        blankTableHtml = blankTableHtml + headers[colIndex];
        blankTableHtml = blankTableHtml + "</th>"
    }

    blankTableHtml = blankTableHtml + "</tr></thead>";
    blankTableHtml = blankTableHtml + "<tbody></tbody>";

    table.html(blankTableHtml);
}

function populateTableRowData(rowData, dataKeys, table) {

    var rowHtml = "<tr>";

    for (var dataKeyIndex in dataKeys) {

        if (!dataKeys.hasOwnProperty(dataKeyIndex)) {
            continue;
        }

        rowHtml = rowHtml
            + "<td>"
            + rowData[dataKeys[dataKeyIndex]]
            + "</td>";
    }

    rowHtml = rowHtml + "</tr>";
    table.find("tr:last").after(rowHtml);
}

function populateTableData(data, datakeys, table) {

    for (var rowIndex in data) {

        if (!data.hasOwnProperty(rowIndex)) {
            continue;
        }

        populateTableRowData(
            data[rowIndex],
            datakeys,
            table);
    }
}

function populateTable(table, headers, datakeys, data) {

    resetToBlankTable(
        table,
        headers);

    populateTableData(
        data,
        datakeys,
        table);
}
