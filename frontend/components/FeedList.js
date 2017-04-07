import React from 'react'
import { render } from 'react-dom'
import FeedListItem from './FeedListItem'
import {API_URL} from '../constants/ApiConfig'

export default class FeedList extends React.Component {
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

        if (!this.state.feeds) return (<p>Loading...</p>);

        var rows = [];
        this.state.feeds.forEach(function(item) {
            rows.push(<FeedListItem url={item.url} name={item.name} isActive={false} />);
        });

        return (
            <ul className="side-nav-bar">
                {rows}
            </ul>
        );
    }
}