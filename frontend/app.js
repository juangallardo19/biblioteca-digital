const API_URL = 'http://localhost:7000/api';

// === UTILITY FUNCTIONS ===
function showNotification(message, type = 'info') {
    console.log(`[${type.toUpperCase()}] ${message}`);
    // You can implement a visual notification system here
}

function showLoading(show) {
    // You can implement loading indicator here
    console.log(show ? 'Loading...' : 'Done');
}

// === API CALLS ===

// Load configuration (Singleton Pattern)
async function loadConfig() {
    try {
        const response = await fetch(`${API_URL}/config`);
        const data = await response.json();

        const configInfo = document.getElementById('config-info');
        if (configInfo) {
            configInfo.innerHTML = `
                <strong>Library Name:</strong> ${data.libraryName}<br>
                <strong>Max Loans:</strong> ${data.maxLoans}<br>
                <strong>DRM Enabled:</strong> ${data.drmEnabled}
            `;
        }

        console.log('Configuration loaded:', data);
    } catch (error) {
        showNotification('Error loading configuration: ' + error.message, 'error');
    }
}

// Load book catalog
async function loadCatalog() {
    try {
        showLoading(true);
        const response = await fetch(`${API_URL}/books`);
        const books = await response.json();

        const catalog = document.getElementById('book-list');
        if (catalog) {
            catalog.innerHTML = '';
            books.forEach(book => {
                const li = document.createElement('li');
                li.innerHTML = `
                    <div>
                        <strong>${book.title}</strong> by ${book.author}
                        <br><small>Genre: ${book.genre} | Format: ${book.format}</small>
                        <br>Status: ${book.available ? '✅ Available' : '❌ Borrowed'}
                    </div>
                    <button onclick="borrowBook(${book.id})" ${!book.available ? 'disabled' : ''}>
                        Borrow
                    </button>
                `;
                catalog.appendChild(li);
            });
        }

        showLoading(false);
        console.log('Catalog loaded:', books.length, 'books');
    } catch (error) {
        showLoading(false);
        showNotification('Error loading catalog: ' + error.message, 'error');
    }
}

// Borrow a book (Facade Pattern)
async function borrowBook(bookId) {
    try {
        const userId = 1; // Default user for demo

        const response = await fetch(`${API_URL}/borrow`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ userId, bookId })
        });

        const result = await response.json();

        if (result.success) {
            showNotification(result.message, 'success');
            await loadCatalog();
            await loadUserLoans();
        } else {
            showNotification(result.message, 'error');
        }
    } catch (error) {
        showNotification('Error borrowing book: ' + error.message, 'error');
    }
}

// Return a book (Facade Pattern)
async function returnBook(loanId) {
    try {
        const response = await fetch(`${API_URL}/return`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ loanId })
        });

        const result = await response.json();

        if (result.success) {
            showNotification(result.message, 'success');
            await loadCatalog();
            await loadUserLoans();
        } else {
            showNotification(result.message, 'error');
        }
    } catch (error) {
        showNotification('Error returning book: ' + error.message, 'error');
    }
}

// Load user loans
async function loadUserLoans() {
    try {
        const userId = 1; // Default user for demo

        const response = await fetch(`${API_URL}/loans/${userId}`);
        const loans = await response.json();

        const loansList = document.getElementById('loans');
        if (loansList) {
            loansList.innerHTML = '';

            const activeLoans = loans.filter(loan => loan.active);

            if (activeLoans.length === 0) {
                loansList.innerHTML = '<li>No active loans</li>';
            } else {
                activeLoans.forEach(loan => {
                    const li = document.createElement('li');
                    li.innerHTML = `
                        <div>
                            <strong>${loan.book.title}</strong>
                            <br><small>Loaned on: ${loan.loanDate}</small>
                        </div>
                        <button onclick="returnBook(${loan.id})">Return</button>
                    `;
                    loansList.appendChild(li);
                });
            }
        }

        console.log('User loans loaded:', activeLoans.length, 'active loans');
    } catch (error) {
        showNotification('Error loading loans: ' + error.message, 'error');
    }
}

// Create collection (Builder Pattern)
async function createCollection() {
    try {
        const name = document.getElementById('collection-name')?.value || 'My Collection';
        const description = document.getElementById('collection-desc')?.value || 'Custom collection';

        // Get selected books (you need to implement checkbox selection in HTML)
        const bookIds = [1, 2, 3]; // Example book IDs

        const response = await fetch(`${API_URL}/collection`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ name, bookIds, description })
        });

        const result = await response.json();

        if (result.success) {
            showNotification(`Collection "${result.data.name}" created with ${result.data.books.length} books`, 'success');
        } else {
            showNotification(result.message, 'error');
        }
    } catch (error) {
        showNotification('Error creating collection: ' + error.message, 'error');
    }
}

// Get recommendations (Strategy Pattern)
async function getRecommendations() {
    try {
        const userId = 1; // Default user
        const strategy = document.getElementById('strategy-select')?.value || 'POPULARITY';

        const response = await fetch(`${API_URL}/recommend`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ userId, strategy })
        });

        const result = await response.json();

        if (result.success) {
            const recommendations = document.getElementById('recommendations');
            if (recommendations) {
                recommendations.innerHTML = '';
                result.data.forEach(book => {
                    const li = document.createElement('li');
                    li.innerHTML = `
                        <span>${book.title} — ${book.author}</span>
                        <span>${book.genre}</span>
                    `;
                    recommendations.appendChild(li);
                });
            }
            showNotification(`${result.data.length} recommendations generated`, 'success');
        } else {
            showNotification(result.message, 'error');
        }
    } catch (error) {
        showNotification('Error getting recommendations: ' + error.message, 'error');
    }
}

// Apply decorators (Decorator Pattern)
async function applyDecorators() {
    try {
        const bookId = 1; // Example book
        const decorators = [];

        if (document.getElementById('decorator-translator')?.checked) {
            decorators.push('TRANSLATOR');
        }
        if (document.getElementById('decorator-dictionary')?.checked) {
            decorators.push('DICTIONARY');
        }
        if (document.getElementById('decorator-highlight')?.checked) {
            decorators.push('HIGHLIGHT');
        }

        const response = await fetch(`${API_URL}/decorate`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ bookId, decorators })
        });

        const result = await response.json();

        if (result.success) {
            const decoratedContent = document.getElementById('decorated-content');
            if (decoratedContent) {
                decoratedContent.textContent = result.data.decoratedContent;
            }
            showNotification('Decorators applied successfully', 'success');
        } else {
            showNotification(result.message, 'error');
        }
    } catch (error) {
        showNotification('Error applying decorators: ' + error.message, 'error');
    }
}

// Get reader info (Factory Method Pattern)
async function getReaderInfo(device) {
    try {
        const response = await fetch(`${API_URL}/reader/${device}`);
        const result = await response.json();

        if (result.success) {
            const readerInfo = document.getElementById('reader-info');
            if (readerInfo) {
                readerInfo.textContent = result.data.info;
            }
            showNotification(`Reader info loaded for ${device}`, 'info');
        } else {
            showNotification(result.message, 'error');
        }
    } catch (error) {
        showNotification('Error getting reader info: ' + error.message, 'error');
    }
}

// === INITIALIZATION ===
function init() {
    console.log('Initializing Digital Library frontend...');

    loadConfig();
    loadCatalog();
    loadUserLoans();

    // Setup event listeners
    const recommendBtn = document.getElementById('recommend-btn');
    if (recommendBtn) {
        recommendBtn.addEventListener('click', getRecommendations);
    }

    const createCollectionBtn = document.getElementById('create-collection-btn');
    if (createCollectionBtn) {
        createCollectionBtn.addEventListener('click', createCollection);
    }

    const applyDecoratorsBtn = document.getElementById('apply-decorators-btn');
    if (applyDecoratorsBtn) {
        applyDecoratorsBtn.addEventListener('click', applyDecorators);
    }

    console.log('Frontend initialized successfully');
}

// Start when DOM is ready
document.addEventListener('DOMContentLoaded', init);
