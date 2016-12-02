import React from 'react'
import { render } from 'react-dom'
import FeedListItem from './FeedListItem'

export default class FeedList extends React.Component {
	render() {
		var ITEMS = [
			{
				"name": "Java Code Geeks",
				"url": "https://www.javacodegeeks.com/feed/"
			},
			{
				"name": "Bussiness Insider",
				"url": "http://feeds2.feedburner.com/businessinsider"
			}
			
		];
		var rows = [];
		ITEMS.forEach(function(item) {
			rows.push(<FeedListItem url={item.url} name={item.name} />);
		});

		return (
			<ul className="side-nav-bar">
				{rows}
			</ul>
		);
	}
}