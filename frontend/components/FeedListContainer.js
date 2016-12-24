import React from 'react'
import { render } from 'react-dom'
import FeedList from './FeedList'
import {API_URL} from '../constants/ApiConfig'

export default class FeedListContainer extends React.Component {
	constructor(props) {
	    super(props);
	    this.state = {feeds: null};
  	}

  	componentDidMount() {
  		$.ajax({
	      url: API_URL + '/feeds',
	      dataType: 'json',
	      cache: false,
	      success: function(data) {
	        this.setState({feeds: data});
	      }.bind(this),
	      error: function(xhr, status, err) {
	        console.error(url, status, err.toString());
	      }.bind(this)
    	});
  	}

  	render() {
  		return (
  			<FeedList feeds = {this.state.feeds} />
  		);
  	}
}