
// 페이지 로드 시 빈 테이블 생성 함수
scwin.createEmptyTable = function() {
    // 빈 데이터 생성 - 계층 구조 기반
    let emptyData = [];
    let rowIndex = 0;
    
    tableStructure.forEach(category => {
        category.items.forEach(item => {
            item.details.forEach(detail => {
                emptyData.push({
                    rowIndex: rowIndex,
                    col1: detail,
                    col2: 0,  // 본인부담금
                    col3: 0,  // 공단부담금
                    col4: 0,  // 전액본인부담
                    col6: 0,  // 선택진료료
                    col7: 0,  // 선택진료료 외
                });
                rowIndex++;
            });
        });
    });
    
    // 계층 구조 테이블 생성
    createHierarchicalTable(emptyData);
}

scwin.createTable = function (result) {
    // 플랫 리스트 생성 (기존 로직과 호환성 유지)
    let rowName = createFlatRowNameList();
    
    // 데이터 처리
    let processedData = [];
    let dataRows = result.data.rows;
    let originalHeaders = result.data.headers;
    
    // 1단계: rowName 순서대로 기본 구조 생성
    let rowNameDataMap = new Map();
    rowName.forEach(function(name, index) {
        rowNameDataMap.set(name, {
            rowIndex: index,
            col1: name,
            col2: 0,  // 본인부담금
            col3: 0,  // 공단부담금
            col4: 0,  // 전액본인부담
            col6: 0,  // 선택진료료
            col7: 0,  // 선택진료료 외
        });
    });
    
    // 2단계: 정확히 일치하는 항목들 처리
    let unmatchedRows = [];
    dataRows.forEach(function(row) {
        if (rowNameDataMap.has(row.col1)) {
            // 정확히 일치하는 경우
            let targetRow = rowNameDataMap.get(row.col1);
            targetRow.col2 = row.col2 || 0;
            targetRow.col3 = row.col3 || 0;
            targetRow.col4 = row.col4 || 0;
            targetRow.col6 = row.col5 || 0;  // 원래 col5를 col6으로
            targetRow.col7 = row.col6 || 0;  // 원래 col6을 col7로
            // col8도 제외항목이므로 0 유지
        } else {
            // 일치하지 않는 경우 나중에 처리
            unmatchedRows.push(row);
        }
    });
    
    // 3단계: 일치하지 않는 항목들을 가장 유사한 rowName에 병합
    unmatchedRows.forEach(function(row) {
        let result = findMostSimilarRowName(row.col1, rowName);
        
        // 유사도가 0.3 이상인 경우에만 병합
        if (result.similarity >= 0.3) {
            let targetRow = rowNameDataMap.get(result.name);
            
            // 0이 아닌 값들만 병합 (기존 값이 0인 경우에만)
            if (targetRow.col2 === 0 && (row.col2 || 0) !== 0) targetRow.col2 = row.col2;
            if (targetRow.col3 === 0 && (row.col3 || 0) !== 0) targetRow.col3 = row.col3;
            if (targetRow.col4 === 0 && (row.col4 || 0) !== 0) targetRow.col4 = row.col4;
            if (targetRow.col6 === 0 && (row.col5 || 0) !== 0) targetRow.col6 = row.col5;
            if (targetRow.col7 === 0 && (row.col6 || 0) !== 0) targetRow.col7 = row.col6;
            
            console.log(`"${row.col1}"을 "${result.name}"에 병합 (유사도: ${result.similarity.toFixed(2)})`);
        } else {
            console.log(`"${row.col1}"은 유사도가 낮아 무시됨 (최대 유사도: ${result.similarity.toFixed(2)})`);
        }
    });
    
    // 4단계: 최종 데이터 배열 생성 (rowName 순서 유지)
    rowName.forEach(function(name) {
        processedData.push(rowNameDataMap.get(name));
    });
    
	// 🔹 열 별 합계를 계산하여 "합계" 행에 반영
	let totalRow = processedData.find(row => row.col1 === '합계');

	if (totalRow) {
	    for (let colIndex = 2; colIndex <= 8; colIndex++) {
	        let sum = 0;
	        processedData.forEach(row => {
	            if (row.col1 !== '합계') {
	                sum += parseFloat(row['col' + colIndex]) || 0;
	            }
	        });
	        totalRow['col' + colIndex] = sum;
	    }
	}
	
    // 계층 구조 테이블 생성 함수 호출
    createHierarchicalTable(processedData);
    
}

// 플랫 리스트 생성 (기존 코드와의 호환성을 위해)
function createFlatRowNameList() {
    let flatList = [];
    tableStructure.forEach(category => {
        category.items.forEach(item => {
            item.details.forEach(detail => {
                flatList.push(detail);
            });
        });
    });
    return flatList;
}

// 계층 구조 테이블 생성 함수
createHierarchicalTable = function (processedData) {
    var tableContainer = document.getElementById('tableContainer');
    tableContainer.innerHTML = '';

    // 테이블 생성
    var table = document.createElement('table');
    table.classList.add('w2tb');
    table.style.borderCollapse = 'collapse';
    table.style.width = '100%';

    // 헤더 구조 만들기
    // 첫 번째 헤더 행 (항목명, 급여, 비급여)
    var headerRow1 = document.createElement('tr');
    
    // 항목명 헤더 (rowspan=2, colspan=3)
    var itemNameTh = document.createElement('th');
    itemNameTh.textContent = '항목명';
    itemNameTh.rowSpan = 3;
    itemNameTh.colSpan = 3;
    itemNameTh.className = "thead1";
    itemNameTh.style.border = '1px solid #ddd';
    itemNameTh.style.padding = '8px';
    itemNameTh.style.backgroundColor = '#f2f2f2';
    itemNameTh.style.textAlign = 'center';
    headerRow1.appendChild(itemNameTh);
    
    // 급여 헤더 (colspan=4)
    var gupyeoTh = document.createElement('th');
    gupyeoTh.textContent = '급여';
    gupyeoTh.colSpan = 4;
    gupyeoTh.className = "thead1";
    gupyeoTh.style.border = '1px solid #ddd';
    gupyeoTh.style.padding = '8px';
    gupyeoTh.style.backgroundColor = '#e8f4fd';
    gupyeoTh.style.textAlign = 'center';
    headerRow1.appendChild(gupyeoTh);
    
    // 비급여 헤더 (colspan=3)
    var bigupyeoTh = document.createElement('th');
    bigupyeoTh.textContent = '비급여';
    bigupyeoTh.colSpan = 3;
    bigupyeoTh.className = "thead1";
    bigupyeoTh.style.border = '1px solid #ddd';
    bigupyeoTh.style.padding = '8px';
    bigupyeoTh.style.backgroundColor = '#fff3cd';
    bigupyeoTh.style.textAlign = 'center';
    headerRow1.appendChild(bigupyeoTh);
    
    table.appendChild(headerRow1);
    
    // 두 번째 헤더 행 (세부 항목들)
    var headerRow2 = document.createElement('tr');
    
    // 급여 하위 - 일부본인부담 헤더 추가
    var ilbuTh = document.createElement('th');
    ilbuTh.className = "thead2";
    ilbuTh.textContent = '일부본인부담';
    ilbuTh.colSpan = 2;
    ilbuTh.style.border = '1px solid #ddd';
    ilbuTh.style.padding = '8px';
    ilbuTh.style.backgroundColor = '#f0f8ff';
    ilbuTh.style.textAlign = 'center';
    headerRow2.appendChild(ilbuTh);
    
    // 급여 하위 나머지 항목들
    var gupyeoOtherHeaders = ['전액본인부담', '제외항목'];
    gupyeoOtherHeaders.forEach(function(header) {
        var th = document.createElement('th');
        th.textContent = header;
        th.className = "thead2";
        th.rowSpan= 2;
        th.style.border = '1px solid #ddd';
        th.style.padding = '8px';
        th.style.backgroundColor = '#f2f2f2';
        th.style.textAlign = 'center';
        headerRow2.appendChild(th);
    });
    
    // 비급여 하위 항목들
    var bigupyeoSubHeaders = ['선택진료료', '선택진료료 외', '제외항목'];
    bigupyeoSubHeaders.forEach(function(header) {
        var th = document.createElement('th');
        th.rowSpan= 2;
        th.className = "thead2";
        th.textContent = header;
        th.style.border = '1px solid #ddd';
        th.style.padding = '8px';
        th.style.backgroundColor = '#f2f2f2';
        th.style.textAlign = 'center';
        headerRow2.appendChild(th);
    });
    
    table.appendChild(headerRow2);
    
    // 세 번째 헤더 행 (일부본인부담 하위)
    var headerRow3 = document.createElement('tr');
    
    // 일부본인부담 하위 항목들
    var ilbuSubHeaders = ['본인부담금', '공단부담금'];
    ilbuSubHeaders.forEach(function(header) {
        var th = document.createElement('th');
        th.textContent = header;
        th.className = "thead3";
        th.style.border = '1px solid #ddd';
        th.style.padding = '8px';
        th.style.backgroundColor = '#f2f2f2';
        th.style.textAlign = 'center';
        headerRow3.appendChild(th);
    });
    
    table.appendChild(headerRow3);

    // 데이터 행 추가 - 계층 구조로
    let dataIndex = 0;
    
    tableStructure.forEach(category => {
        let categoryRowAdded = false;
        
        category.items.forEach(item => {
            let subcategoryRowAdded = false;
            
            // details가 하나뿐이고 subcategory와 같은지 확인
            let shouldMergeSubcategory = (item.details.length === 1 && item.details[0] === item.subcategory);
            
            item.details.forEach(detail => {
                var rowElement = document.createElement('tr');
				
				// 합계 하이라이팅
				if (detail === '합계') {
				    rowElement.style.backgroundColor = '#f2f2f2';
				    rowElement.style.fontWeight = 'bold';
				}

                
                // 대분류 컬럼 (첫 번째 행에만 표시, rowspan 적용)
                if (!categoryRowAdded) {
                    var categoryTd = document.createElement('td');
                    categoryTd.textContent = category.category;
                    categoryTd.rowSpan = category.rowspan;
                    categoryTd.style.border = '1px solid #ddd';
                    categoryTd.style.padding = '8px';
                    categoryTd.style.backgroundColor = '#e8f4fd';
                    categoryTd.style.textAlign = 'center';
                    categoryTd.style.verticalAlign = 'middle';
                    categoryTd.style.fontWeight = 'bold';
                    rowElement.appendChild(categoryTd);
                    categoryRowAdded = true;
                }
                
                // 중분류 컬럼 (첫 번째 행에만 표시, rowspan 적용)
                if (!subcategoryRowAdded && !shouldMergeSubcategory) {
                    var subcategoryTd = document.createElement('td');
                    subcategoryTd.textContent = item.subcategory;
                    if (item.rowspan > 1) {
                        subcategoryTd.rowSpan = item.rowspan;
                    }
                    subcategoryTd.style.border = '1px solid #ddd';
                    subcategoryTd.style.padding = '8px';
                    subcategoryTd.style.backgroundColor = '#f0f8ff';
                    subcategoryTd.style.textAlign = 'center';
                    subcategoryTd.style.verticalAlign = 'middle';
                    rowElement.appendChild(subcategoryTd);
                    subcategoryRowAdded = true;
                }
                
                // 항목명 컬럼 (subcategory와 같으면 colspan=2)
                var itemTd = document.createElement('td');
                itemTd.textContent = detail;
                if (shouldMergeSubcategory) {
                    itemTd.colSpan = 2;
                }
                itemTd.style.border = '1px solid #ddd';
                itemTd.style.padding = '8px';
                itemTd.style.backgroundColor = '#f9f9f9';
                rowElement.appendChild(itemTd);
                
                // 데이터 컬럼들 (col2~col8)
                let currentRowData = processedData[dataIndex];
                for (let colIndex = 2; colIndex <= 8; colIndex++) {
                    var td = document.createElement('td');
                    var cellValue = currentRowData['col' + colIndex] !== undefined ? currentRowData['col' + colIndex] : 0;
                    
                    td.style.border = '1px solid #ddd';
                    td.style.padding = '8px';
                    td.style.textAlign = 'right';
                    
                    // 헤더 인덱스 계산 (항목명 제외하고 0부터 시작)
                    let headerIndex = colIndex - 2;
                    
                    // 제외항목 컬럼인지 확인 (colIndex 5, 8이 제외항목 = headerIndex 3, 6)
                    if (headerIndex === 3 || headerIndex === 6) {
						// 제외항목 컬럼 - 모달 열기
						const key = `${dataIndex}_${headerIndex}`;
						    
						let displayValue = cellValue; 
						    
						// exclusionData에 해당 키의 데이터가 있으면 우선 사용
						if (scwin.exclusionData && scwin.exclusionData[key] && scwin.exclusionData[key].trim()) {
						    displayValue = scwin.exclusionData[key];
						}
						    
						td.textContent = displayValue;
						    
						td.style.backgroundColor = '#fff3cd';
						td.style.cursor = 'pointer';
						    
						// 제외항목 클릭 이벤트
						td.addEventListener('click', createExclusionClickHandler(dataIndex, headerIndex, td));
						    
                    } else {
                        // 수정 가능한 컬럼
                        td.textContent = cellValue;
                        td.style.cursor = 'pointer';
                        
                        // 일반 셀 클릭 이벤트
                        td.addEventListener('click', createCellClickHandler(td, dataIndex, headerIndex, cellValue));
                        
						if (currentRowData.col1 !== '합계') {
							// 호버 효과
							td.addEventListener('mouseenter', function() {
								this.style.backgroundColor = '#f0f0f0';
							});
							td.addEventListener('mouseleave', function() {
								this.style.backgroundColor = 'white';
							});
						}
                        
                    }
                    
                    rowElement.appendChild(td);
                }
                
                table.appendChild(rowElement);
                dataIndex++;
            });
        });
    });

    // 테이블을 컨테이너에 추가
    tableContainer.appendChild(table);
	
	for (let i=0; i<8; i++){
		scwin.updateColumnTotal(i);
	}
	
		
	calculateMainTableTotal();
}


// 제외항목 클릭 핸들러 생성 함수 
function createExclusionClickHandler(rowIndex, colIndex, td) {
    return function() {
        openExclusionModal(rowIndex, colIndex, td);
    };
}

// 일반 셀 클릭 핸들러 생성 함수 
function createCellClickHandler(td, rowIndex, colIndex, cellValue) {
    return function() {
        editCell(td, rowIndex, colIndex, td.textContent);
    };
}

// 문자열 유사도 계산 (Levenshtein Distance 기반)
function calculateSimilarity(str1, str2) {
    const matrix = [];
    
    // 빈 문자열 처리
    if (str1.length === 0) return str2.length;
    if (str2.length === 0) return str1.length;
    
    // 행렬 초기화
    for (let i = 0; i <= str2.length; i++) {
        matrix[i] = [i];
    }
    for (let j = 0; j <= str1.length; j++) {
        matrix[0][j] = j;
    }
    
    // 거리 계산
    for (let i = 1; i <= str2.length; i++) {
        for (let j = 1; j <= str1.length; j++) {
            if (str2.charAt(i - 1) === str1.charAt(j - 1)) {
                matrix[i][j] = matrix[i - 1][j - 1];
            } else {
                matrix[i][j] = Math.min(
                    matrix[i - 1][j - 1] + 1, // 교체
                    matrix[i][j - 1] + 1,     // 삽입
                    matrix[i - 1][j] + 1      // 삭제
                );
            }
        }
    }
    
    // 유사도를 0~1 사이의 값으로 변환
    const maxLength = Math.max(str1.length, str2.length);
    return (maxLength - matrix[str2.length][str1.length]) / maxLength;
}

// 가장 유사한 rowName 항목을 찾는 함수
function findMostSimilarRowName(col1Value, rowName) {
    let maxSimilarity = 0;
    let mostSimilar = rowName[0];
    
    rowName.forEach(function(name) {
        const similarity = calculateSimilarity(col1Value, name);
        if (similarity > maxSimilarity) {
            maxSimilarity = similarity;
            mostSimilar = name;
        }
    });
    
    return { name: mostSimilar, similarity: maxSimilarity };
}

// 서브테이블계산 
function calculateMainTableTotal() {
    let total = 0;
	let patientTotal = 0
    const mainTable = document.querySelector('#tableContainer table');
    
    if (mainTable) {
        // "합계" 행 찾기
        const rows = mainTable.rows;
		for (let i = 3; i < rows.length; i++) { // 헤더 3개 행 제외
			const firstCell = rows[i].cells[rows[i].cells.length - 8]; 
		    if (firstCell && firstCell.textContent.includes('합계')) {
				for (let j = 0; j < 7; j++) { // col2~col8
					if (j !== 3 && j !== 6) { // 제외항목 컬럼(col5, col8) 제외
						const cellIndex = rows[i].cells.length - 7 + j;
		                if (cellIndex < rows[i].cells.length - 1) {
							const cellValue = parseFloat(rows[i].cells[cellIndex].textContent) || 0;
		                    total += cellValue;
							if (j !== 1) {
								patientTotal += cellValue;
							}
							console.log(total, patientTotal);
		                }
		            }
		        }
		    }
			if (firstCell && firstCell.textContent.includes("상한액초과금")) {
				const overLimitValueText = rows[i].cells[rows[i].cells.length - 7].textContent.trim();
				const overLimitValue = parseFloat(overLimitValueText.replace(/,/g, '')) || 0;
				patientTotal -= overLimitValue;
			}
        }
    }
	
    ipt_tableTotal.setValue(total);
	ipt_tableTotalminus.setValue(patientTotal);
}

function saveCellValue(td, input, rowIndex, colIndex) {
    const newValue = parseFloat(input.value) || 0;
    td.textContent = newValue;
    
    console.log(`Row ${rowIndex}, Col ${colIndex} 값 변경: ${newValue}`);
    
	scwin.updateColumnTotal(colIndex);
	
	calculateMainTableTotal();
}

// 제외항목 모달 열기 함수
function openExclusionModal(rowIndex, colIndex, td) {
	const key = `${rowIndex}_${colIndex}`;
	
	localStorage.setItem('itemId', rowIndex);
	localStorage.setItem('excId', colIndex);
	// todo: treatmentId localStorage 저장..
	localStorage.setItem("treatmentId", 1);
	
	requires("uiplugin.popup"); 
	var winWid = $(window).width();
	var winHei = $(window).height();
	var popWid = 1000;
	var popHei = 600;
	var sumLeft = (winWid - popWid) / 2;
	var sumTop = (winHei - popHei) / 2;
	
	var opts = {
					id : "exc_popup",
					width : popWid + "px",
					height : popHei + "px",
					top : sumTop, 
					left : sumLeft,
					popupName : `제외항목 ${key}`,
					modal : true, 
					type : "wframePopup", 
					closeAction: function() {
						let newValue = localStorage.getItem("exc");
						scwin.updateExclusionCell(rowIndex, colIndex, newValue);
						scwin.exclusionData[key] = newValue;
						localStorage.removeItem("exc");
						return true;
					}
				};
				
	$p.openPopup("/InsWebApp/ui/audit/exec-popup.xml", opts);
}

scwin.updateColumnTotal = function (colIndex) {
	colIndex += 1;
    const mainTable = document.querySelector('#tableContainer table');
    if (!mainTable) return;

    const rows = mainTable.rows;
    let sum = 0;
    let totalRow = null;

    for (let i = 3; i < rows.length; i++) { // 헤더 3행 스킵
        const row = rows[i];
        const labelCell = row.cells[row.cells.length - 8]; // 'col1' 셀
        const targetCell = row.cells[row.cells.length - 8 + colIndex]; // 해당 열 셀

        if (!labelCell) continue;

        if (labelCell.textContent.trim() === '합계') {
            totalRow = row; 
            break;
        }

        if (targetCell) {
            const cellValue = parseFloat(targetCell.textContent.replace(/,/g, '')) || 0;
            sum += cellValue;
        }
    }

    // 계산된 합계를 합계행에 반영
    if (totalRow) {
        const totalCell = totalRow.cells[totalRow.cells.length - 8 + colIndex];
        if (totalCell) {
            totalCell.textContent = sum;
        }
    }
}


// 셀 값 수정 함수
function editCell(td, rowIndex, colIndex, currentValue) {
    // 이미 수정 중인 셀이 있으면 취소
    const existingInput = document.querySelector('.editing-input');
    if (existingInput) {
        existingInput.parentNode.textContent = existingInput.getAttribute('data-original');
        existingInput.remove();
    }
    
    // 입력 필드 생성
    const input = document.createElement('input');
    input.type = 'number';
    input.value = currentValue;
    input.className = 'editing-input';
    input.setAttribute('data-original', currentValue);
    input.style.width = '100%';
    input.style.border = '1px solid #007bff';
    input.style.padding = '2px';
    input.style.textAlign = 'right';
    input.style.boxSizing = 'border-box';
    
    // 기존 텍스트 숨기기
    td.textContent = '';
    td.appendChild(input);
    input.focus();
    input.select();
    
    // Enter 키로 저장
    input.addEventListener('keypress', function(e) {
        if (e.key === 'Enter') {
            saveCellValue(td, input, rowIndex, colIndex);
        }
    });
    
    // ESC 키로 취소
    input.addEventListener('keydown', function(e) {
        if (e.key === 'Escape') {
            td.textContent = currentValue;
        }
    });
    
    // 포커스 잃으면 저장
    input.addEventListener('blur', function() {
        saveCellValue(td, input, rowIndex, colIndex);
    });
	
	// todo: 합계행 저장
	
	// todo: 진료비 총액, 환자부담 총액 저장

}


