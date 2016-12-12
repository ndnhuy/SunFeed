import React from 'react'
import { render } from 'react-dom'
import { Link } from 'react-router'

export default class FeedListItem extends React.Component {
	render() {
		return (
			<li>
				<Link to={{ pathname: '/feed', query: { url: this.props.url } }} activeStyle={{ color: 'red' }}>{this.props.name}</Link>
			</li>
		)
	}
}
