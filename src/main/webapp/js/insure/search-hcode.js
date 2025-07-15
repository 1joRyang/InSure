function mergeUniqueRows(arr1, arr2) {
    var seen = {};
    var merged = [];

    function add(row) {
        var key = row.code + "::" + row.des;
        if (!seen[key]) {
            seen[key] = true;
            merged.push(row);
        }
    }
    arr1.forEach(add);
    arr2.forEach(add);

    return merged;
}