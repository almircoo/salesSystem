(function () {
  const nav = document.querySelector('.nav-underline');
  if (!nav) return;

  const tabs = Array.from(nav.querySelectorAll('[data-filter]'));
  const ordersContainer = document.getElementById('ordersList');
  const orderItems = () => Array.from(ordersContainer.querySelectorAll('.order-item'));

  // Actualiza contadores
  function updateCounts() {
    const counts = { all: 0, pending: 0, shipping: 0, delivered: 0 };
    orderItems().forEach(it => {
      counts.all++;
      const s = (it.getAttribute('data-status') || '').toLowerCase();
      if (s && counts[s] !== undefined) counts[s]++;
    });
    document.getElementById('count-all').textContent = counts.all;
    document.getElementById('count-pending').textContent = counts.pending;
    document.getElementById('count-shipping').textContent = counts.shipping;
    document.getElementById('count-delivered').textContent = counts.delivered;
  }

  // Aplica filtro
  function applyFilter(filter) {
    orderItems().forEach(it => {
      const status = (it.getAttribute('data-status') || '').toLowerCase();
      if (filter === 'all' || status === filter) {
        it.style.display = '';
      } else {
        it.style.display = 'none';
      }
    });
  }

  // Manejo de clicks en tabs
  nav.addEventListener('click', (e) => {
    const btn = e.target.closest('[data-filter]');
    if (!btn) return;
    e.preventDefault();

    // actualizar aria-selected y clase active
    tabs.forEach(t => {
      const active = t === btn;
      t.classList.toggle('active', active);
      t.setAttribute('aria-selected', active ? 'true' : 'false');
    });

    const filter = btn.getAttribute('data-filter');
    applyFilter(filter);
  });

  // inicializar
  updateCounts();
  applyFilter('all');
})();
