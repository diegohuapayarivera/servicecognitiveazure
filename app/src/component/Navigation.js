import React, { Component } from 'react';

class Navigation extends Component {
    render() {
        return (
            <div>
                <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
                    <div className="container-fluid">
                        <a className="navbar-brand" href="/">FrontApp</a>
                        <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                            <span className="navbar-toggler-icon"></span>
                        </button>
                        <div className="collapse navbar-collapse" id="navbarNav">
                            <ul className="navbar-nav">
                                <li className="nav-item">
                                    <a className="navbar-brand" href="/">Predecir</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </nav>
            </div>
        );
    }
}

export default Navigation;