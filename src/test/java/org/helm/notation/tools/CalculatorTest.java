package org.helm.notation.tools;

import static org.testng.AssertJUnit.assertEquals;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import java.io.IOException;

import org.helm.notation.CalculationException;
import org.helm.notation.MonomerException;
import org.helm.notation.NotationException;
import org.helm.notation.StructureException;
import org.jdom2.JDOMException;

public class CalculatorTest {

	private ExtinctionCoefficientCalculator calculator;

	@BeforeMethod
	public void setUp() throws CalculationException {
		calculator = ExtinctionCoefficientCalculator.getInstance();
	}

	@Test
	public void testCalculateFromAminoAcidSequence()
			throws CalculationException {

		String input = "AGGDDDDDDDDDDDDDDDDDDFFFFFFFFFFFFF";
		float result = calculator.calculateFromAminoAcidSequence(input);
		assertEquals(0.0, result, 1e-15);

		input = "AGGCFFFFFFFFFF";
		result = calculator.calculateFromAminoAcidSequence(input);
		assertEquals(0.0, result, 62.5);

		input = "AGGYEEEEEEEEEEEEEEEEEEE";
		result = calculator.calculateFromAminoAcidSequence(input);
		assertEquals(1490.0, result, 1e-15);

		input = "AGGWEEEEEEEEEEEEEEEEEEE";
		result = calculator.calculateFromAminoAcidSequence(input);
		assertEquals(5500.0, result, 1e-15);
	}

	@Test
	public void testCalculateFromPeptidePolymerNotation()
			throws NotationException, MonomerException, CalculationException,
			IOException, JDOMException, StructureException {
		String input = "A.G.G.W.E.E.E.E.E.W";
		float result = calculator.calculateFromPeptidePolymerNotation(input);
		assertEquals(11000.0, result, 1e-15);

	}

	@Test
	public void testCalculateFromComplexNotation() throws NotationException,
			MonomerException, IOException, JDOMException, StructureException,
			CalculationException {
		String input = "PEPTIDE1{A.G.G.W.E.E.E.E.E.W}$$$$";
		float result = calculator.calculateFromComplexNotation(input,
				ExtinctionCoefficientCalculator.PEPTIDE_UNIT_TYPE);
		assertEquals(11000.0, result, 1e-15);

		input = "PEPTIDE1{A.G.G.W.E.E.E.E.E.W}|PEPTIDE2{A.G.G.W.E.Y.E.E.E.E.W}$$$$";
		result = calculator.calculateFromComplexNotation(input);
		assertEquals(23.49, result, 1e-6);

		input = "PEPTIDE1{A.G.G.W.E.E.E.E.E.W}|PEPTIDE2{A.G.G.W.E.Y.E.E.E.E.W}$$$$";
		result = calculator.calculateFromComplexNotation(input,
				ExtinctionCoefficientCalculator.PEPTIDE_UNIT_TYPE);
		assertEquals(23490.0, result, 1e-15);

		input = "RNA1{P.R(A)P.R([5meC])P.R(G)P.[mR](A)}$$$$";
		result = calculator.calculateFromComplexNotation(input);
		assertEquals(46.200005, result, 1e-6);

		input = "RNA1{P.R(A)P.R([5meC])P.R(G)P.[mR](A)}$$$$";
		result = calculator.calculateFromComplexNotation(input,
				ExtinctionCoefficientCalculator.PEPTIDE_UNIT_TYPE);
		assertEquals(46200.004, result, 1e-4);

		input = "RNA1{P.R(A)P.R([5meC])P.R(G)P.[mR](A)}|CHEM1{PEG2}|PEPTIDE1{A.G.G.W.E.E.E.E.E.W}|PEPTIDE2{A.G.G.W.E.Y.E.E.E.E.W}$$$$";
		result = calculator.calculateFromComplexNotation(input);
		assertEquals(69.69, result, 1e-5);

	}

	@Test
	public void testCalculateFromNucleotideSequence()
			throws CalculationException {
		String input = "ACGTACGT";
		float result = calculator.calculateFromNucleotideSequence(input);
		assertEquals(81.1199951171875, result, 1e-6);

	}

	@Test
	public void testCalculateFromModifiedNucleotideSequence()
			throws CalculationException, NotationException, IOException,
			JDOMException {
		String input = "ACGmTACmGT";
		float result = calculator
				.calculateFromModifiedNucleotideSequence(input);
		assertEquals(81.1199951171875, result, 1e-6);

	}

	@Test
	public void testCalculateFromRnaPolymerNotation() throws NotationException,
			MonomerException, CalculationException, IOException, JDOMException,
			StructureException {
		String input = "P.R(A)P.R(C)P.R(G)P.[mR](A)";
		float result = calculator.calculateFromRnaPolymerNotation(input);
		assertEquals(46.200005, result, 1e-6);

	}

}
