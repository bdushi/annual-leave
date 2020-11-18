import App from './App.svelte';
import 'bootstrap/dist/css/bootstrap.css';
// import 'bootstrap/dist/css/bootstrap.min.css';

const app = new App({
	target: document.body,
	props: {
		name: 'ANNULA LEAVES'
	}
});

export default app;