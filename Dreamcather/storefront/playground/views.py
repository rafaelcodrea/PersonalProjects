from django.http import HttpResponse
from django.shortcuts import render
from .models import Dream
import matplotlib.pyplot as plt
import seaborn as sns
from io import BytesIO
import base64
import numpy as np
from django.shortcuts import redirect

# Create your views here.
#request handler
#request -> response

import logging
logger = logging.getLogger("mylogger")


def chart_redirect(request):
    metric = request.GET.get('metric')
    if metric == 'duration':
        return redirect('duration_chart')
    
    elif metric == 'energy':
        return redirect('energy_chart')
    
    elif metric == 'stress':
        return redirect('stress_level_chart')
    
    else:

     
        return render(request, 'error.html', {'message': 'Please select a metric.'})
    


def duration_chart(request):
   
    dreams = Dream.objects.all().order_by('id')
    durations = [dream.duration for dream in dreams]
    num_dreams = np.arange(len(dreams)) + 1
    
    fig, ax = plt.subplots()
    ax.plot(num_dreams, durations, marker='o', linestyle='--')

    ax.set_title('Duration Chart')
    ax.set_xlabel('Entry')
    ax.set_ylabel('Duration of Dream')
   
   
    buf = BytesIO()
    plt.savefig(buf, format='png')
    plt.close(fig)
    
    data = base64.b64encode(buf.getbuffer()).decode('ascii')
    return render(request, 'duration_chart.html', {'data': data})


def stress_level_chart(request):
  
    dreams = Dream.objects.all().order_by('id')
    stresses = [dream.stress_level for dream in dreams]
    num_dreams = np.arange(len(dreams)) + 1
  
    fig, ax = plt.subplots()
    ax.plot(num_dreams, stresses , marker='o', linestyle='--')
    ax.set_xlabel('Entry')
    ax.set_ylabel('Stress level of the dream')
    ax.set_title('Stress Level Chart')

    buf = BytesIO()
    plt.savefig(buf, format='png')
    plt.close(fig)
    data = base64.b64encode(buf.getbuffer()).decode('ascii')
    return render(request, 'stress_level_chart.html', {'data': data})

def energy_chart(request):
    dreams = Dream.objects.all().order_by('id')
    energies = [dream.energy for dream in dreams]
    num_dreams = np.arange(len(dreams)) + 1
    
    fig, ax = plt.subplots()
    ax.plot(num_dreams, energies , marker='o', linestyle='--')
    ax.set_xlabel('Entry')
    ax.set_ylabel('Energy of the dream')
    ax.set_title('Energy Chart')

    buf = BytesIO()
    plt.savefig(buf, format='png')
    plt.close(fig)
    data = base64.b64encode(buf.getbuffer()).decode('ascii')
    return render(request, 'energy_chart.html', {'data': data})



#chart frumos:
# def duration_chart(request):
#     # get the data from the database
#     durations = Dream.objects.values_list('duration', flat=True)
    
#     # create a kernel density estimate
#     sns.set(style='whitegrid')
#     fig, ax = plt.subplots()
#     sns.kdeplot(durations, shade=True, ax=ax)
#     ax.set_xlabel('Duration of dream')
#     ax.set_ylabel('Density')
#     ax.set_title('Duration Chart')

#     # convert the plot to a Django-compatible format
#     from io import BytesIO
#     import base64
#     buf = BytesIO()
#     plt.savefig(buf, format='png')
#     plt.close(fig)
#     data = base64.b64encode(buf.getbuffer()).decode('ascii')
#     return render(request, 'duration_chart.html', {'data': data})



# #incercare 2 charturi:
# def duration2(request):
#     entries = Dream.objects.filter(category="duration")
#     duration_values = [entry.duration for entry in entries]
#     # Create the bar chart
#     plt.bar(range(len(duration_values)), duration_values)
#     plt.xticks(range(len(duration_values)), ['Entry 1', 'Entry 2', 'Entry 3'])
#     plt.xlabel('Entry')
#     plt.ylabel('Duration')

#     # Save the chart to a file
#     plt.savefig('/path/to/chart.png')


# def duration_chart(request):
#     # get the data from the database
#     durations = Dream.objects.values_list('duration', flat=True)
    
#     # create a histogram
#     fig, ax = plt.subplots()
#     ax.hist(durations, bins=[1, 2, 3, 4, 5, 6], align='left')
#     ax.set_xticks([1, 2, 3, 4, 5])
#     ax.set_xticklabels(['1', '2', '3', '4', '5'])
#     ax.set_xlabel('Duration of dream')
#     ax.set_ylabel('Night')
#     ax.set_title('Duration Chart')

#     # convert the plot to a Django-compatible format
#     from io import BytesIO
#     import base64
#     buf = BytesIO()
#     plt.savefig(buf, format='png')
#     plt.close(fig)
#     data = base64.b64encode(buf.getbuffer()).decode('ascii')
#     return render(request, 'duration_chart.html', {'data': data})


# def stress_level_chart(request):
#     # get the data from the database
#     durations = Dream.objects.values_list('stress_level', flat=True)
    
#     # create a histogram
#     fig, ax = plt.subplots()
#     ax.hist(durations, bins=[1, 2, 3, 4, 5, 6], align='left')
#     ax.set_xticks([1, 2, 3, 4, 5])
#     ax.set_xticklabels(['1', '2', '3', '4', '5'])
#     ax.set_xlabel('Stress level')
#     ax.set_ylabel('Night')
#     ax.set_title('Stress level chart:')

#     # convert the plot to a Django-compatible format
#     from io import BytesIO
#     import base64
#     buf = BytesIO()
#     plt.savefig(buf, format='png')
#     plt.close(fig)
#     data = base64.b64encode(buf.getbuffer()).decode('ascii')
#     return render(request, 'stress_level_chart.html', {'data': data})


# def energy_chart(request):
#     # get the data from the database
#     durations = Dream.objects.values_list('energy', flat=True)
    
#     # create a histogram
#     fig, ax = plt.subplots()
#     ax.hist(durations, bins=[1, 2, 3, 4, 5, 6], align='left')
#     ax.set_xticks([1, 2, 3, 4, 5])
#     ax.set_xticklabels(['1', '2', '3', '4', '5'])
#     ax.set_xlabel('Energy level')
#     ax.set_ylabel('Night')
#     ax.set_title('Energy chart:')

#     # convert the plot to a Django-compatible format
#     from io import BytesIO
#     import base64
#     buf = BytesIO()
#     plt.savefig(buf, format='png')
#     plt.close(fig)
#     data = base64.b64encode(buf.getbuffer()).decode('ascii')
#     return render(request, 'energy_chart.html', {'data': data})




def index(request):
    if request.method == 'POST':
        description = request.POST['description']
        duration = request.POST['duration']
        stress_level = request.POST['stress_level']
        energy = request.POST['energy']
        
        dream = Dream(description=description, duration=duration, stress_level=stress_level, energy=energy)
        logger.info("Whatever to log")
        dream.save()
        return render(request, 'hello.html')
    
    # If the request is a GET request, render the index template
    return render(request, 'hello.html')

def say_hello(request):
    return render(request, 'hello.html', {'name': 'Rafael'})
