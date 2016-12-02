import React from 'react'
import FeedContent from './FeedContent'
import FeedList from './FeedList'

export default class Home extends React.Component {
	render() {
		return (
			<div>
				<FeedList />
				<FeedContent />
			</div>
		);
	}
}