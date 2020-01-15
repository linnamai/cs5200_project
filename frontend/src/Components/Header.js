import React, { Component } from 'react';
import { Link } from 'react-router-dom';

class Header extends Component {

    render() {
        return (
            <div className="header-container">
                <div className="form-inline">
                    <Link to="/">Home</Link>
                    <div className="user-nav">
                        <Link to="/myReservation">My Reservations</Link>
                        <Link to="/getOrderByCustomerId"> My Orders</Link>
                        <Link to="/myAccount">My account</Link>
                    </div>
                </div>
            </div>
        );
    }
}

export default Header;