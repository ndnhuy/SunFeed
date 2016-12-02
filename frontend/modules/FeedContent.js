import React from 'react'
import { render } from 'react-dom'

export default class EntryTitle extends React.Component {
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