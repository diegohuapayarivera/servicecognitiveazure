import React, { Component } from 'react';
import axios from "axios";

class Predecir extends Component {
    state = {
        url: "",
        img: "",
        predictions: []
    }

    onSubmit = async (e) => {
        e.preventDefault();
        const res = await axios.post("http://localhost:8080/predecir", {
            "url": this.state.img
        })
        // console.log(res.data)
        this.setState((state) => {
            return { predictions: res.data.predictions }
        })
        console.log(this.state.predictions)

    }
    onInputChange = (e) => {
        this.setState({
            img: e.target.value
        });
        // console.log(this.state)
        // console.log(e.target.value)
    }

    render() {
        const { predictions } = this.state
        return (
            <div className="container">
                <div className="row">
                    <div className="col-5">
                        <div className="card card-body">
                            <h4>URL</h4>
                            <form onSubmit={this.onSubmit}>
                                <div className="input-group">
                                    <input type="text"
                                        className="form-control"
                                        placeholder="URL"
                                        name="url"

                                        onChange={this.onInputChange} />
                                    <button className="btn btn-outline-secondary">Enviar</button>
                                </div>
                            </form>


                        </div>
                        <div className="card card-body">
                            <h4>Predeccion</h4>
                            <table>
                                <thead>
                                    <tr>
                                        <th scope="col">Tag   </th>
                                        <th scope="col">Probabilidad</th>
                                    </tr>
                                </thead>
                                <tbody>  
                                    {
                                        predictions.map((data, index) => (
                                            <tr key={data.tagId} scope="col">
                                                <td>{data.tagName}</td>
                                                <td>{data.probability * 100}%</td>
                                            </tr>
                                        ))
                                    }
                                </tbody>
                            </table>

                        </div>
                    </div>
                    <div className="col-7">
                        <div className="card card-body">
                            <h4>Imagen</h4>
                            <div className="card card-body">
                                <img src={this.state.img} alt="" />
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        );
    }
}

export default Predecir;