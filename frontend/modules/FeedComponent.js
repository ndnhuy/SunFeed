import React from 'react'
import { render } from 'react-dom'

class EntryTitle extends React.Component {
  render() {
    return (
      <div className="title">
        <h1>{this.props.title}</h1>
      </div>
    );
  }
}

class EntryDescription extends React.Component {
  render() {
    var desc = this.props.desc;
    return (
      <div className="description" dangerouslySetInnerHTML={{__html: desc}}>
      </div>
    );
  }
}

class EntryMetadata extends React.Component {
  render() {
    return (
      <div className="metadata">
        <span><b>{this.props.feedName}</b></span>
        <span> / </span>
        <span><b>{this.props.lifeTime}</b></span>
      </div>
    )
  }
}

class Entry extends React.Component {
  render() {
    return (
      <div>
        <EntryTitle title={this.props.entry.title} />
        <EntryDescription desc={this.props.entry.description} />
        <EntryMetadata feedName={this.props.entry.feedName} lifeTime={this.props.entry.lifeTime} />
      </div>
    );
  }
}

class FeedContent extends React.Component {

  render() {
    if (!this.props.feed || !this.props.feed.entries) return (<div>Loading...</div>);

    var entries = [];
    this.props.feed.entries.forEach(function(entry) {
      entries.push(<Entry entry={entry} title={entry.title} desc={entry.description} />);
    });
    return (
      <div>{entries}</div>
    );
  };
}

class FeedSearchBox extends React.Component {
  constructor(props) {
    super(props);
    this.state = {feedUrl: ''};
  }

  handleSearchTextChange(e) {
    this.setState({feedUrl: e.target.value});
  }

  handleSubmit(e) {
    e.preventDefault();
    var feedUrl = this.state.feedUrl.trim();
    
    if (!feedUrl) return;

    this.props.onSearchingFeed(feedUrl);
  }

  render() {
    return (
      <form className="searchBox" onSubmit={this.handleSubmit.bind(this)} >
        <input 
          type="text" 
          placeholder="Enter feed url..." 
          value={this.state.feedUrl}
          onChange={this.handleSearchTextChange.bind(this)} />
        <input type="submit" value="Search" />
      </form>
    );
  }
}

export default class FeedComponent extends React.Component {
  constructor(props) {
    super(props);
    this.state = {feed: null};
  }

  handleSearchingFeed(feedUrl) {
    this.loadFeed('http://localhost:8080/me?url=' + feedUrl + '&descSize=300');
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
        console.error(this.props.url, status, err.toString());
      }.bind(this)
    });
  }

  render() {
    return (
      <div className="content">
        <FeedSearchBox onSearchingFeed={this.handleSearchingFeed.bind(this)} />
        <FeedContent feed={this.state.feed} />
      </div>
    );
  }
}