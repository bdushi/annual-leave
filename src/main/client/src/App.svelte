<!-- https://css-tricks.com/everything-you-need-to-know-about-date-in-javascript/ -->
<script>
import { onMount } from "svelte";
import axios from "axios";
	export let name;
	let leaves = [];
	axios.defaults.headers.common['Authorization'] = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJicnVub2R1c2hpIiwibmJmIjoxNjA0OTI2ODA2LCJpZCI6MSwiZXhwIjoxNjA1MDEzMjA2LCJpYXQiOjE2MDQ5MjY4MDYsImF1dGhvcml0aWVzIjpbIkVNUExPWUVFIl0sInVzZXJuYW1lIjoiYnJ1bm9kdXNoaSJ9.QWUZ0cT2Rs0Qahif69aL_LLhwa-Z1WklmupPnMV0F00Hm6i5y0js0Ta1qskpO59BVfB4lhrCUw0YYRrJpyeZ1dhXnI4dgcg790GHGjL58T3hPJprvxmx0rsHH0cmHk1N8eJnp9Q_xaG3CntlozlRq1E-2OGZ3PBFxvYMw77dGQ18lNJ4uFP73GcIE-NNrSFHNmVct3whKqqEg7CUFRYyQc3r7sEPl3OXyOS4A3-TZmmae73M6s8WOpNJWGNRPJnN0bp17cYzuy9BiJ7d_wB4GVpAVN3v31AEv2-v1Gxb_YPrRyCSLaByIokccI4XU5OETmEE-u1vlkgpihyy4VREZA";
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
</script>

<main>
	<h1>{name}</h1>
	{#if leaves.length !== 0}
	<table>
		<tr>
			<th>ID</th>
			<th>Requested By</th>
			<th>Requested Date</th>
			<th>Start Date</th>
			<th>End Date</th>
			<th>Leave Type</th>
			<th>Leave Description</th>
			<th>Leave Comment</th>
			<th>Approved</th>
		</tr>
		{#each leaves as leave , i (i)}
		<tr class:odd={i%2}>
			<td>{i + 1}</td>
			<td>{leave.requestedBy.username}</td>
			<td>{new Date(leave.createDate).toLocaleString('en-GB')} </td>
			<td>{new Date(leave.startDate).toLocaleString('en-GB')} </td>
			<td>{new Date(leave.endDate).toLocaleString('en-GB')} </td>
			<td>{leave.leaveTypes.description}</td>
			<td>{leave.description}</td>
			<td>{leave.comment}</td>
		</tr> 
		{/each}
	</table>
	{:else}
		<p>Loading.....</p>
	{/if}
</main>

<style>
	main {
		padding: 1em;
		margin: 0 auto;
	}

	h1 {
		text-align: left center;
		color: #ff3e00;
		text-transform: uppercase;
		font-size: 2em;
		font-weight: 100;
	}

	table {
		border-collapse: collapse;
		border-spacing: 0;
		empty-cells: show;
		align-items: center;
		table-layout: fixed;
	}
	table th {
		background-color: #eee;
	}
	table th, table td {
		padding: 10px;
		margin-left: 10px;
		text-align: left;
	}
	table td input {
		margin-bottom: 0;
	}
	table tr.odd {
		background-color: #eee;
	}

	@media (min-width: 640px) {
		main {
			max-width: none;
		}
	}
</style>