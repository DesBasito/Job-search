const cardElement = document.getElementById('page-list');
const pagingButtons = document.getElementById('paging-buttons');
const urlVacancies = `api/vacancies/paging`;
let pageNum = 0;
let sortingBy1 = sortingBy
const form = document.getElementById('searchVaca');
const searchInput = document.querySelector("[data-search]")


searchInput.addEventListener("input", (e) => {
    const value = e.target.value;
    form.querySelector('input[name="vacancy"]').value = value;
    if (value !== null) {
        search(value).then(r => new Error().cause);
    } else {
        fetchAndRender(urlVacancies).then(r => new Error().cause)
    }
});

async function search(value) {
    let url = `/api/vacancies/search?title=${value}`
    const data = await fetch(url)
    const dataJson = await data.json();
    if (dataJson && Array.isArray(dataJson.content)) {
        renderVacancies(dataJson.content);
    } else {
        console.error('Invalid data format received from the server');
    }
}

window.onload = async () => {
    await fetchAndRender(urlVacancies);
    pagingButtons.innerHTML = `
    <div class="my-2 text-success">${vacancyPage}: <span id="pagination-number">${pageNum}</span></div>
       <ul class="pagination">
            <li class="page-item page-link" style="cursor: pointer" onclick="switchPage(pageNum - 1, sortingBy1, 'previous')">&laquo;</li>
            <li class="page-item page-link" style="cursor: pointer" onclick="switchPage(1, sortingBy1, '1')">1</a></li>
            <li class="page-item page-link" style="cursor: pointer" onclick="switchPage(2, sortingBy1, '2')">2</a></li>
            <li class="page-item page-link" style="cursor: pointer" onclick="switchPage(3, sortingBy1, '3')">3</a></li>
            <li class="page-item page-link" style="cursor: pointer" onclick="switchPage(pageNum + 1, sortingBy1, 'next')">&raquo;</li>
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
    let salary;
    if (vacancy.salary == null || vacancy.salary === 0) {
        salary = negotiable;
    } else {
        salary = vacancy.salary;
    }
    return `
        <div class="card mb-3">
            <div class="card-body">
                <a href="#" class="text-decoration-none text-dark">${vacancy.authorEmail}</a>
                <h3 class="mt-2 mb-5"><a href="vacancies/info/${vacancy.id}" class="text-decoration-none text-bg-dark rounded-2 px-3 py-1">${vacancy.name}</a></h3>
                <h5 class="card-title">${vacancy.description}</h5>
                <p class="card-text">Salary: ${salary}</p>
                <p class="text-end">${vacancy.updateTime}</p>
            </div>
        </div>
    `;
}

async function switchPage(pageNumber, sortingBy, pageFunc) {
    const pag = document.getElementById('pagination-number')
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
    pag.innerHTML = ''
    pag.innerHTML = pageNum
    let vacValue = form.querySelector('input[name="vacancy"]').value;
    if (vacValue.trim().length !== 0) {
        console.log(vacValue)
        let url = `/api/vacancies/search?title=${vacValue}&page=${pageNum}`
        await fetchAndRender(url);
    } else {
        const url = `${urlVacancies}?page=${pageNum}&filter=${sortingBy}`;
        await fetchAndRender(url);
    }
}
