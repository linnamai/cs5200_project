import React, { Component } from 'react';
import { Link } from 'react-router-dom';

class MerchantHeader extends Component {

    render() {
        return (
            <div className="merchant-header-container">
                <div className="form-inline">
                    <Link to="/merchant">Merchant Home</Link>
                    <div className="user-nav">
                        <Link to="/merchant">My Restaurants</Link>
                        <Link to="/reviewsForMyRestaurant"> My Reviews</Link>
                        <Link to="/ordersForMyRestaurant"> My Orders</Link>

                    </div>
                </div>
            </div>
        );
    }
}

export default MerchantHeader;
