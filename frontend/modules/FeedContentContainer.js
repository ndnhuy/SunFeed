import React from 'react'
import { render } from 'react-dom'
import FeedContent from './FeedContent'

export default class FeedContentContainer extends React.Component {
	constructor(props) {
	    super(props);
	    this.state = {feed: null};
  	}

	componentDidMount() {
		this.loadFeed('http://localhost:8080/me?url=' + this.props.location.query.url + '&descSize=300');
	}

	componentWillReceiveProps(nextProps) {
		if (this.props == nextProps) return;

		this.loadFeed('http://localhost:8080/me?url=' + nextProps.location.query.url + '&descSize=300');
	}

	loadFeed(url) {
	    $.ajax({
	      url: url,
	      dataType: 'json',
	      cache: false,
	      success: function(feedData) {
	        this.setState({feed: feedData});
	      }.bind(this),
	      error: function(xhr, status, err) {
	        console.error(url, status, err.toString());
	      }.bind(this)
    	});
  	}

  	render() {
  		
  		console.log('render');

  		return (
			<FeedContent feed={this.state.feed} />
  		);
  	}
}