const cardElement = document.getElementById('page-list');
const pagingButtons = document.getElementById('paging-buttons');
const urlVacancies = `api/vacancies/paging`;
let pageNum = 0;
let sortingBy1 = sortingBy
window.onload = async () => {
    await fetchAndRender(urlVacancies);
    pagingButtons.innerHTML = `
       <ul class="pagination">
            <li class="page-item page-link" style="cursor: pointer" onclick="switchPage(pageNum - 1, sortingBy1, 'previous')">Previous</li>
            <li class="page-item page-link" style="cursor: pointer" onclick="switchPage(1, sortingBy1, '1')">1</a></li>
            <li class="page-item page-link" style="cursor: pointer" onclick="switchPage(2, sortingBy1, '2')">2</a></li>
            <li class="page-item page-link" style="cursor: pointer" onclick="switchPage(3, sortingBy1, '3')">3</a></li>
            <li class="page-item page-link" style="cursor: pointer" onclick="switchPage(pageNum + 1, sortingBy1, 'next')">Next</li>
        </ul>
    `
};

async function fetchAndRender(url) {
    try {
        const response = await fetch(url);
        const dataVacancies = await response.json();
        console.log(dataVacancies)

        if (dataVacancies && Array.isArray(dataVacancies.content)) {
            renderVacancies(dataVacancies.content);
        } else {
            console.error('Invalid data format received from the server');
        }
    } catch (error) {
        console.error('Error fetching or processing data:', error);
    }
}

function renderVacancies(vacancies) {
    cardElement.innerHTML = '';
    vacancies.forEach(vacancy => {
        const card = createVacancyCard(vacancy);
        cardElement.innerHTML += card;
    });
}

function createVacancyCard(vacancy) {
    return `
        <div class="card mb-3">
            <div class="card-body">
                <a href="#" class="text-decoration-none text-dark">${vacancy.authorEmail}</a>
                <h3 class="mt-2 mb-5"><a href="vacancies/info/${vacancy.id}" class="text-decoration-none text-bg-dark rounded-2 px-3 py-1">${vacancy.name}</a></h3>
                <h5 class="card-title">${vacancy.description}</h5>
                <p class="card-text">Salary: ${vacancy.salary}</p>
                <p class="text-end">${vacancy.updateTime}</p>
            </div>
        </div>
    `;
}

async function switchPage(pageNumber, sortingBy, pageFunc) {
    switch (pageFunc) {
        case 'previous':
            pageNum = Math.max(0, pageNum - 1);
            break;
        case 'next':
            pageNum++;
            break;
        default:
            pageNum = parseInt(pageFunc);
            break;
    }
    const url = `${urlVacancies}?page=${pageNum}&filter=${sortingBy}`;
    await fetchAndRender(url);
}
