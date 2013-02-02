package roo.app.web;

import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import roo.app.domain.Task;
        
/**
 * A central place to register application Converters and Formatters. 
 */
public class ApplicationConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean {

	@Override
	protected void installFormatters(FormatterRegistry registry) {
		super.installFormatters(registry);
		// Register application converters and formatters
	}
	

	public void installLabelConverters(FormatterRegistry registry) {
        registry.addConverter(new TaskConverter());
    }

	@Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
        installLabelConverters(getObject());
    }

	static class TaskConverter implements Converter<Task, String>  {
        public String convert(Task task) {
            return new StringBuilder().append(task.getDescription()).append(" ").append(task.getDueDate()).toString();
        }
        
    }
}
