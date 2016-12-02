import React from 'react'
import { render } from 'react-dom'
import Home from './modules/Home'
import { Router, Route, hashHistory } from 'react-router'
// <Route path="/feedEx" component={FeedComponent} />
render ((
	<Router history={hashHistory}>
		<Route path="/" component={Home} />
		
	</Router>
), document.getElementById('container'))