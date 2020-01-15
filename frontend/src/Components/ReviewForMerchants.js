import React, {Component} from "react";
import MerchantHeader from "./MerchantHeader";

class ReviewForMerchants extends Component {
    constructor() {
        super();
        this.state = {
            restaurant:"",
            reviews:""
        };
        this.getData()
    }

    getData () {
        //get restaurant
        let key = 'merchantId';
        let value = 3;
        const init = {
            method: 'GET',
        };
        fetch(
            `http://localhost:8080/getRestaurantsByMerchantId?${key}=${value}`, init
        ).then(res => res.json())
            .then(data => {
                this.setState({
                                  restaurant: data
                              })
            }).catch(e => console.log('error', e));
    }

    render() {
        //get reviews for restaurant
        const reviewsElements = [];
        const init = {
            method: 'GET',
        };

        if (this.state.restaurant !== "") {
            let key = 'restaurantId';
            let value = this.state.restaurant.restaurantId;
            fetch(
                `http://localhost:8080/getReviewsByRestaurantId?${key}=${value}`, init
            ).then(res => res.json())
                .then(data => {
                    this.setState({
                                      reviews: data
                                  })

                }).catch(e => console.log('error', e));
        }

        if (this.state.reviews !== "") {
            for (let review of this.state.reviews) {
                reviewsElements.push(
                    <tr>
                        <td>{this.state.restaurant.name}</td>
                        <td>{review.rating}</td>
                        <td>{review.content}</td>
                        <td>{review.createDate}</td>
                    </tr>
                )
            }
        }

        return (
            <div>
                <MerchantHeader/>
                <div className="wrapper">
                    <div className="container-fluid">
                            <div className="panel panel-default" id="menu-panel">
                                <p>Review:</p>

                                <table className="table" id="review-table">
                                    <th>Restaurant Name</th>
                                    <th>Rating</th>
                                    <th>Content</th>
                                    <th>Created Date</th>
                                    {reviewsElements}
                                </table>

                            </div>
                        </div>
                    </div>
            </div>
        );
    }
}

export default ReviewForMerchants;
