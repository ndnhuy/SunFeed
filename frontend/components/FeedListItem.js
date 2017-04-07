import React from 'react'
import { render } from 'react-dom'
import { Link } from 'react-router'

export default class FeedListItem extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <li onClick={this.handleClick}>
                <Link to={{ pathname: '/feed', query: { url: this.props.url } }}
                      activeClassName='active'>{this.props.name}</Link>
            </li>
        )
    }
}