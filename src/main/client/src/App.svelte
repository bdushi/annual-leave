<script>
import { onMount } from "svelte";
import axios from "axios";;
	export let name;
	let leaves = [];
	axios.defaults.headers.common['Authorization'] = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJicnVub2R1c2hpIiwibmJmIjoxNjA0NTkxNjg1LCJpZCI6MSwiZXhwIjoxNjA0Njc4MDg1LCJpYXQiOjE2MDQ1OTE2ODUsImF1dGhvcml0aWVzIjpbIkVNUExPWUVFIl0sInVzZXJuYW1lIjoiYnJ1bm9kdXNoaSJ9.RbywQ-PLHIv2lFtT2CfAKlAVQTydpKXG7TcDZHVFsXDILjJnnxjOraqCNBWOdqfUi6vsaamgKLlTc2hXdg1Sn8C7mwP0nJ1YHtyqSw5FvHSfparyqoyfC1azST_0Afo7Rp0O-s00MlOlIVC0rBU7Z0X0NYRo46emt3QXPAqs-ZaZYXjtCSnQ6Saih17xHkXImwkNTSWCry-wyYKSg1zIu3Y5a48I6xxQRVrisdEp_U7HY1p9pCCI6g6mJQz_1qMRecg12yAgvbrJ93HoCMpaLcRDJa1lw4aRQPvkWUEr3yFgoHWptxzBRyd8342LG-O2O3MMlPzyeGlGMnQw18lhAw";
	onMount(async () => {
		axios
		.get("http://localhost:8080/leave")
		.then(response => {
			leaves = response.data
			console.log(leaves)
		})
		.catch(error => {
			console.log(error)
      });
	});
	console.log("Test")
</script>

<main>
	<h1>Hello {name}!</h1>
	<p>Visit the <a href="https://svelte.dev/tutorial">Svelte tutorial</a> to learn how to build Svelte apps.</p>
	<div>
		{#if leaves}
		<ul style="line-height:180%">
			{#each leaves as leave}
			<li>
				{leave.requestedBy.username} {leave.createDate} {leave.leaveTypes.description} {leave.description} {leave.comment}
			</li>
			{/each}
		</ul>
		{:else}
		  <p>Loading.....</p>
		{/if}
	</div>
</main>

<style>
	main {
		text-align: center;
		padding: 1em;
		max-width: 240px;
		margin: 0 auto;
	}

	h1 {
		color: #ff3e00;
		text-transform: uppercase;
		font-size: 4em;
		font-weight: 100;
	}

	@media (min-width: 640px) {
		main {
			max-width: none;
		}
	}
</style>