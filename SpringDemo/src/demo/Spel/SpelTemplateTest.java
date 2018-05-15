package demo.Spel;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class SpelTemplateTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        ExpressionParser parser = new SpelExpressionParser();
        
        ParserContext parserContext = new ParserContext() {
            @Override
            public boolean isTemplate() {
                return true;
            }
            @Override
            public String getExpressionPrefix() {
                return "%{";
            }
            @Override
            public String getExpressionSuffix() {
                return "}";
            }
        };
        String template = "%{'Hello '}%{'World!'}";
        Expression expression = parser.parseExpression(template, parserContext);
        System.out.println(expression.getValue(String.class));
	}

}
