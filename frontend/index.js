import React from 'react'
import { render } from 'react-dom'
import Home from './components/Home'
import FeedContentContainer from './components/FeedContentContainer'
import { Router, Route, hashHistory } from 'react-router'

render ((
	<Router history={hashHistory}>
		<Route path="/" component={Home}>
			<Route path="feed" component={FeedContentContainer} />
		</Route>
	</Router>
), document.getElementById('container'))