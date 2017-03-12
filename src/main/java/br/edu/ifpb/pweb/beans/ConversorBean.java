package br.edu.ifpb.pweb.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "conversorBean")
@ViewScoped
public class ConversorBean {
	private double celcius;
	private double fahrenheit;
	private double cToF;
	private double fToC;

	public double getCelcius() {
		return celcius;
	}

	public void setCelcius(double celcius) {
		this.celcius = celcius;
	}

	public double getFahrenheit() {
		return fahrenheit;
	}

	public void setFahrenheit(double fahrenheit) {
		this.fahrenheit = fahrenheit;
	}

	public double getcToF() {
		return cToF;
	}

	public void setcToF(double cToF) {
		this.cToF = cToF;
	}

	public double getfToC() {
		return fToC;
	}

	public void setfToC(double fToC) {
		this.fToC = fToC;
	}

	public String converter() {
		this.cToF = (this.celcius * 1.8) + 32;
		this.fToC = 0.55555555556 * (this.fahrenheit - 32);

		return null;
	}

}
