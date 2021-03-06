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
      <div className="description">
        {this.props.desc}
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
        <span><b>{moment(this.props.publishedDateMilli).toNow(true)}</b></span>
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
        <EntryMetadata feedName={this.props.entry.feedName} publishedDateMilli={this.props.entry.publishedDateMilli} />
      </div>
    );
  }
}

export default class FeedContent extends React.Component {
  render() {
    if (!this.props.entries) return (<div className='content'>Loading...</div>);

    var entries = [];
    this.props.entries.forEach(function(entry) {
      entries.push(<Entry entry={entry} />);
    });
    return (
      <div className='content'>{entries}</div>
    );
  };
}