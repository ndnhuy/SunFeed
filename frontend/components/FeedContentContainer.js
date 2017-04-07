import React from 'react'
import { render } from 'react-dom'
import FeedContent from './FeedContent'
import {API_URL} from '../constants/ApiConfig'

export default class FeedContentContainer extends React.Component {
	constructor(props) {
	    super(props);
	    this.state = {entries: null};
  	}

	componentWillReceiveProps(nextProps) {
		if (this.props == nextProps) return;

		this.loadFeed(API_URL + '/feeds/search?url=' + nextProps.location.query.url + '&descSize=300');
	}

	loadFeed(url) {
	    $.ajax({
	      url: url,
	      dataType: 'json',
	      cache: false,
	      success: function(feedData) {
	        this.setState({entries: feedData});
	      }.bind(this),
	      error: function(xhr, status, err) {
	        console.error(url, status, err.toString());
	      }.bind(this)
    	});
  	}

  	render() {
  		return (
			<FeedContent entries={this.state.entries} />
  		);
  	}
}