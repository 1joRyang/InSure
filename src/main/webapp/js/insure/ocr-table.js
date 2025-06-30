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
                    col5: 0,  // 제외항목 (첫 번째)
                    col6: 0,  // 선택진료료
                    col7: 0,  // 선택진료료 외
                    col8: 0   // 제외항목 (두 번째)
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
            col5: 0,  // 제외항목 (첫 번째)
            col6: 0,  // 선택진료료
            col7: 0,  // 선택진료료 외
            col8: 0   // 제외항목 (두 번째)
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
            // col5는 제외항목이므로 0 유지
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
    
    // 진료비총액 업데이트
    setTimeout(updateTotalAmount, 200);
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
				console.log(itemTd);
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
                        if (exclusionData[key] && exclusionData[key].trim()) {
                            td.textContent = '상세보기';
                            td.style.color = '#007bff';
                            td.style.textDecoration = 'underline';
                        } else {
                            td.textContent = cellValue;
                        }
                        
                        td.style.backgroundColor = '#fff3cd';
                        td.style.cursor = 'pointer';
                        
                        // 제외항목 클릭 이벤트
                        td.addEventListener('click', createExclusionClickHandler(dataIndex, headerIndex, cellValue));
                        
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
}

// 서브테이블 생성 함수
scwin.createSubTable = function () {
    const subTableContainer = document.getElementById('subTable');
    subTableContainer.innerHTML = '';

    const table = document.createElement('table');
    table.classList.add('w2tb');
    table.style.borderCollapse = 'collapse';
    table.style.width = '100%';

    // 헤더 생성
    const headerRow = document.createElement('tr');
    
    // 항목명 헤더
    const itemHeader = document.createElement('th');
    itemHeader.textContent = '구분';
    itemHeader.style.border = '1px solid #ddd';
    itemHeader.style.padding = '8px';
    itemHeader.style.backgroundColor = '#f2f2f2';
    itemHeader.style.textAlign = 'center';
    itemHeader.style.width = '50%';
    headerRow.appendChild(itemHeader);
    
    // 금액산정내용 헤더
    const amountHeader = document.createElement('th');
    amountHeader.textContent = '금액산정내용';
    amountHeader.style.border = '1px solid #ddd';
    amountHeader.style.padding = '8px';
    amountHeader.style.backgroundColor = '#f2f2f2';
    amountHeader.style.textAlign = 'center';
    amountHeader.style.width = '50%';
    headerRow.appendChild(amountHeader);
    
    table.appendChild(headerRow);

    // 데이터 행 생성
    scwin.subTableTitle.forEach((title, index) => {
        const row = document.createElement('tr');
        
        // 구분 컬럼
        const titleTd = document.createElement('td');
        titleTd.textContent = title;
        titleTd.style.border = '1px solid #ddd';
        titleTd.style.padding = '8px';
        titleTd.style.backgroundColor = '#f9f9f9';
        titleTd.style.textAlign = 'center';
        row.appendChild(titleTd);
        
        // 금액산정내용 컬럼
        const amountTd = document.createElement('td');
        amountTd.textContent = subTableData[title];
        amountTd.style.border = '1px solid #ddd';
        amountTd.style.padding = '8px';
        amountTd.style.textAlign = 'right';
        amountTd.style.cursor = 'pointer';
        
        
            // 수정 가능한 셀
            amountTd.addEventListener('click', function() {
                editSubTableCell(amountTd, title, subTableData[title]);
            });
            
            // 호버 효과
            amountTd.addEventListener('mouseenter', function() {
                if (title !== '진료비총액') {
                    this.style.backgroundColor = '#f0f0f0';
                }
            });
            amountTd.addEventListener('mouseleave', function() {
                if (title !== '진료비총액') {
                    this.style.backgroundColor = 'white';
                }
            });
        
        
        row.appendChild(amountTd);
        table.appendChild(row);
    });

    subTableContainer.appendChild(table);
}

// 결과테이블 생성 함수
scwin.createResultTable = function() {
    const resultTableContainer = document.getElementById('resultTable');
    resultTableContainer.innerHTML = '';

    const table = document.createElement('table');
    table.classList.add('w2tb');
    table.style.borderCollapse = 'collapse';
    table.style.width = '100%';
    table.style.marginTop = '20px';

    // 헤더 생성
    const headerRow = document.createElement('tr');
    
    // 항목명 헤더
    const itemHeader = document.createElement('th');
    itemHeader.textContent = '구분';
    itemHeader.style.border = '1px solid #ddd';
    itemHeader.style.padding = '8px';
    itemHeader.style.backgroundColor = '#f2f2f2';
    itemHeader.style.textAlign = 'center';
    itemHeader.style.width = '50%';
    headerRow.appendChild(itemHeader);
    
    // 금액산정내용 헤더
    const amountHeader = document.createElement('th');
    amountHeader.textContent = '금액산정내용';
    amountHeader.style.border = '1px solid #ddd';
    amountHeader.style.padding = '8px';
    amountHeader.style.backgroundColor = '#f2f2f2';
    amountHeader.style.textAlign = 'center';
    amountHeader.style.width = '50%';
    headerRow.appendChild(amountHeader);
    
    table.appendChild(headerRow);

    // 데이터 행 생성
    scwin.resultTable.forEach((title, index) => {
        const row = document.createElement('tr');
        
        // 구분 컬럼
        const titleTd = document.createElement('td');
        titleTd.textContent = title;
        titleTd.style.border = '1px solid #ddd';
        titleTd.style.padding = '8px';
        titleTd.style.backgroundColor = '#f9f9f9';
        titleTd.style.textAlign = 'center';
        row.appendChild(titleTd);
        
        // 금액산정내용 컬럼
        const amountTd = document.createElement('td');
        amountTd.textContent = resultTableData[title];
        amountTd.style.border = '1px solid #ddd';
        amountTd.style.padding = '8px';
        amountTd.style.textAlign = 'right';
        amountTd.style.cursor = 'pointer';
        
        // 수정 가능한 셀
        amountTd.addEventListener('click', function() {
            editResultTableCell(amountTd, title, resultTableData[title]);
        });
        
        // 호버 효과
        amountTd.addEventListener('mouseenter', function() {
            this.style.backgroundColor = '#f0f0f0';
        });
        amountTd.addEventListener('mouseleave', function() {
            this.style.backgroundColor = 'white';
        });
        
        row.appendChild(amountTd);
        table.appendChild(row);
    });

    resultTableContainer.appendChild(table);
}

// 서브테이블 데이터 저장 객체
let subTableData = {
    "진료비총액": 0,
    "환자부담총액": 0,
    "이미납부한금액": 0,
    "납부할금액": 0,
    "납부한금액": 0
};

// 결과테이블 데이터 저장 객체
let resultTableData = {
    "최종수납액": 0,
    "병원공제금": 0,
    "삭감항목총액": 0,
    "산출금": 0
};

// 제외항목 클릭 핸들러 생성 함수 
function createExclusionClickHandler(rowIndex, colIndex, cellValue) {
    return function() {
        openExclusionModal(rowIndex, colIndex, cellValue);
    };
}

// 일반 셀 클릭 핸들러 생성 함수 
function createCellClickHandler(td, rowIndex, colIndex, cellValue) {
    return function() {
        editCell(td, rowIndex, colIndex, cellValue);
    };
}

// 문자열 유사도를 계산하는 함수 (Levenshtein Distance 기반)
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

// 메인 테이블에서 합계 계산 함수
function calculateMainTableTotal() {
    let total = 0;
    const mainTable = document.querySelector('#tableContainer table');
    
    if (mainTable) {
        // "합계" 행 찾기
        const rows = mainTable.rows;
        for (let i = 3; i < rows.length; i++) { // 헤더 3개 행 제외
            const firstCell = rows[i].cells[rows[i].cells.length - 8]; // 항목명 셀 (맨 뒤에서 8번째)
            if (firstCell && firstCell.textContent.includes('합계')) {
                // 합계 행에서 모든 숫자 컬럼 합산 (col2~col8, 제외항목 제외)
                for (let j = 0; j < 7; j++) { // col2~col8
                    if (j !== 3 && j !== 6) { // 제외항목 컬럼(col5, col8) 제외
                        const cellIndex = rows[i].cells.length - 7 + j;
                        if (cellIndex < rows[i].cells.length) {
                            const cellValue = parseFloat(rows[i].cells[cellIndex].textContent) || 0;
                            total += cellValue;
                        }
                    }
                }
                break;
            }
        }
    }
    
    return total;
}

// 서브테이블 셀 수정 함수
function editSubTableCell(td, title, currentValue) {
    // 진료비총액은 수정 불가
    if (title === '진료비총액') {
        return;
    }
    
    // 이미 수정 중인 셀이 있으면 취소
    const existingInput = document.querySelector('.editing-subtable-input');
    if (existingInput) {
        existingInput.parentNode.textContent = existingInput.getAttribute('data-original');
        existingInput.remove();
    }
    
    // 입력 필드 생성
    const input = document.createElement('input');
    input.type = 'number';
    input.value = currentValue;
    input.className = 'editing-subtable-input';
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
            saveSubTableCellValue(td, input, title);
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
        saveSubTableCellValue(td, input, title);
    });
}

// 서브테이블 셀 값 저장 함수
function saveSubTableCellValue(td, input, title) {
    const newValue = parseFloat(input.value) || 0;
    td.textContent = newValue;
    subTableData[title] = newValue;
    
    console.log(`서브테이블 ${title} 값 변경: ${newValue}`);
}

// 결과테이블 셀 수정 함수
function editResultTableCell(td, title, currentValue) {
    // 이미 수정 중인 셀이 있으면 취소
    const existingInput = document.querySelector('.editing-resulttable-input');
    if (existingInput) {
        existingInput.parentNode.textContent = existingInput.getAttribute('data-original');
        existingInput.remove();
    }
    
    // 입력 필드 생성
    const input = document.createElement('input');
    input.type = 'number';
    input.value = currentValue;
    input.className = 'editing-resulttable-input';
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
            saveResultTableCellValue(td, input, title);
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
        saveResultTableCellValue(td, input, title);
    });
}

// 결과테이블 셀 값 저장 함수
function saveResultTableCellValue(td, input, title) {
    const newValue = parseFloat(input.value) || 0;
    td.textContent = newValue;
    resultTableData[title] = newValue;
    
    console.log(`결과테이블 ${title} 값 변경: ${newValue}`);
}

// 실시간 진료비총액 업데이트 함수
function updateTotalAmount() {
    const total = calculateMainTableTotal();
    subTableData['진료비총액'] = total;
    
    // 서브테이블의 진료비총액 셀 업데이트
    const subTable = document.querySelector('#subTable table');
    if (subTable) {
        const rows = subTable.rows;
        for (let i = 1; i < rows.length; i++) {
            const titleCell = rows[i].cells[0];
            if (titleCell && titleCell.textContent === '진료비총액') {
                const amountCell = rows[i].cells[1];
                if (amountCell) {
                    amountCell.textContent = total;
                }
                break;
            }
        }
    }
}

// 기존 saveCellValue 함수 수정 - 실시간 업데이트 추가
function saveCellValue(td, input, rowIndex, colIndex) {
    const newValue = parseFloat(input.value) || 0;
    td.textContent = newValue;
    
    console.log(`Row ${rowIndex}, Col ${colIndex} 값 변경: ${newValue}`);
    
    // 실시간으로 진료비총액 업데이트
    setTimeout(updateTotalAmount, 100); // 약간의 지연을 두어 DOM 업데이트 후 실행
}

// 제외항목 모달 열기 함수
function openExclusionModal(rowIndex, colIndex, currentValue) {
    // 기존 모달이 있으면 제거
    const existingModal = document.getElementById('exclusionModal');
    if (existingModal) {
        existingModal.remove();
    }
    
    // 기존 제외항목 데이터 가져오기
    const key = `${rowIndex}_${colIndex}`;
    const existingDetails = exclusionData[key] || '';
    
    // 모달 HTML 생성
    const modalHtml = `
        <div id="exclusionModal" style="
            position: fixed; 
            top: 0; 
            left: 0; 
            width: 100%; 
            height: 100%; 
            background-color: rgba(0,0,0,0.5); 
            display: flex; 
            justify-content: center; 
            align-items: center;
            z-index: 1000;
        ">
            <div id="modalContent" style="
                background: white; 
                padding: 20px; 
                border-radius: 5px; 
                width: 400px;
                box-shadow: 0 4px 6px rgba(0,0,0,0.1);
            ">
                <h3>제외항목 상세</h3>
                <p>현재 값: <span id="currentValue">${currentValue}</span></p>
                <textarea id="exclusionDetails" placeholder="제외항목 상세 내용을 입력하세요..." style="
                    width: 100%; 
                    height: 100px; 
                    margin: 10px 0;
                    padding: 10px;
                    border: 1px solid #ccc;
                    border-radius: 3px;
                    resize: vertical;
                    box-sizing: border-box;
                ">${existingDetails}</textarea>
                <div style="text-align: right; margin-top: 15px;">
                    <button id="cancelBtn" style="
                        margin-right: 10px;
                        padding: 8px 16px;
                        background: #ccc;
                        border: none;
                        border-radius: 3px;
                        cursor: pointer;
                    ">취소</button>
                    <button id="saveBtn" style="
                        padding: 8px 16px;
                        background: #007bff;
                        color: white;
                        border: none;
                        border-radius: 3px;
                        cursor: pointer;
                    ">저장</button>
                </div>
            </div>
        </div>
    `;
    
    document.body.insertAdjacentHTML('beforeend', modalHtml);
    
    // 이벤트 리스너 추가
    document.getElementById('cancelBtn').addEventListener('click', closeExclusionModal);
    document.getElementById('saveBtn').addEventListener('click', function() {
        saveExclusionDetails(rowIndex, colIndex);
    });
    
    // 모달 배경 클릭 시 닫기 (모달 내용 클릭은 제외)
    document.getElementById('exclusionModal').addEventListener('click', function(e) {
        if (e.target.id === 'exclusionModal') {
            closeExclusionModal();
        }
    });
    
    // ESC 키로 닫기
    const escapeHandler = function(e) {
        if (e.key === 'Escape') {
            closeExclusionModal();
            document.removeEventListener('keydown', escapeHandler);
        }
    };
    document.addEventListener('keydown', escapeHandler);
    
    // 텍스트영역에 포커스
    document.getElementById('exclusionDetails').focus();
}

// 제외항목 모달 닫기 함수
function closeExclusionModal() {
    const modal = document.getElementById('exclusionModal');
    if (modal) {
        modal.remove();
    }
}

// 제외항목 데이터를 저장할 전역 객체
let exclusionData = {};

// 제외항목 상세 저장 함수
function saveExclusionDetails(rowIndex, colIndex) {
    const details = document.getElementById('exclusionDetails').value;
    const key = `${rowIndex}_${colIndex}`;
    
    // 제외항목 데이터 저장
    exclusionData[key] = details;
    
    // 해당 셀 찾기 - 더 정확한 선택자 사용
    const targetTable = document.querySelector('#tableContainer table');
    if (targetTable) {
        // 행 인덱스는 헤더 행 3개를 고려하여 +3
        const targetRow = targetTable.rows[rowIndex + 3];
        if (targetRow) {
            // 컬럼 인덱스는 항목명 컬럼 3개를 고려하여 +3
            const targetCell = targetRow.cells[colIndex + 3];
            if (targetCell) {
                if (details.trim()) {
                    targetCell.textContent = '상세보기';
                    targetCell.style.color = '#007bff';
                    targetCell.style.textDecoration = 'underline';
                } else {
                    targetCell.textContent = '0';
                    targetCell.style.color = 'black';
                    targetCell.style.textDecoration = 'none';
                }
            }
        }
    }
    
    console.log(`Row ${rowIndex}, Col ${colIndex}에 제외항목 상세 저장: ${details}`);
    
    closeExclusionModal();
    alert('제외항목 상세가 저장되었습니다.');
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
}


